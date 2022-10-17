package starterrestapimongodbspringboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import starterrestapimongodbspringboot.configuration.ObjectMapper;
import starterrestapimongodbspringboot.dto.UserRequest;
import starterrestapimongodbspringboot.entity.User;
import starterrestapimongodbspringboot.repository.UserRepository;
import starterrestapimongodbspringboot.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code Prism Technologies Pvt. Ltd.
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * This method will create new entry to user DB table
     * @param userRequest {@link UserRequest}
     * @return {@link User}
     */
    @Transactional
    public User signUp (UserRequest userRequest){
        log.info("Entered into signUp service method. user displayName was :: {} ", userRequest.getUserName());
        // Step 1 : converting dto to entity
        User user = objectMapper.modelMapper().map(userRequest, User.class);
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        user.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth()));
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("Etc/UTC")));
        // Step 2 : validating language and country
        Locale locale = new Locale(userRequest.getLanguage(),userRequest.getCountry());
        user.setLanguage(locale.getISO3Language().toUpperCase());
        user.setCountry(locale.getISO3Country());
        // Step 3 : saving user details on MongoDB server
        User savedUser = userRepository.insert(user);
        log.info("User details was saved successfully. saved user details was :: {} ", savedUser);
        // Step 4 : returning user saved details
        return savedUser;
    }

    /**
     * This method used to update user details if exist in DB or else signUp
     * @param userRequest {@link UserRequest}
     * @return {@link User}
     */
    @Transactional
    public User updateSignUp(UserRequest userRequest) {
        log.info("Entered into updateSignUp. requested userId was :: {} ",userRequest.getUserId());
        // Step 1 : validating user Id in DB
        return userRepository.findById(userRequest.getUserId()).map(user -> {
            Locale locale = new Locale(userRequest.getLanguage(),userRequest.getCountry());
            // Step 2 : updating existing user object to new updated object
            user.setUserName(userRequest.getUserName());
            user.setEmail(userRequest.getEmail());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setAddress(userRequest.getAddress());
            user.setCity(userRequest.getCity());
            user.setState(userRequest.getState());
            user.setCountry(locale.getISO3Country());
            user.setGender(userRequest.getGender());
            user.setDateOfBirth(LocalDate.parse(userRequest.getDateOfBirth()));
            user.setCreatedAt(LocalDateTime.now(ZoneId.of("Etc/UTC")));
            user.setLanguage(locale.getISO3Language().toUpperCase());
            user.setRole(userRequest.getRole());
            // Step 3 : saving updated user details in DB
            return userRepository.save(user);
            // Step 4 : else making new entry to DB
        }).orElseGet(() -> this.signUp(userRequest));
    }

    /**
     * This method used get one user details by userId
     * @return {@link List<User>}
     */
    @Transactional(readOnly = true)
    public User getUserById(String userId) {
        log.info("Entered into getUser service method. Requested user Id was :: {} ",userId);
        return this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("could not found user details in DB for requested userId "+userId));
    }

    /**
     * This method used get whole user list
     * @return {@link List<User>}
     */
    @Transactional(readOnly = true)
    public Page<User> getAllUser(Integer pageNumber, Integer pageSize) {
        log.info("Entered into getAllUser service method. user requested count was");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        return this.userRepository.findAll(pageable);
    }

    /**
     * This method used to delete user details in DB
     * @param userId
     * @return {@link String}
     */
    @Transactional
    public String deleteUser(String userId) {
        log.info("Entered into deleteUser service method. userId was :: {} ",userId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "User deleted successfully in DB. deleted userId was :: "+ userId +" !";
        } else {
            return "Could not found requested userId :: "+userId+" !";
        }
    }
}
