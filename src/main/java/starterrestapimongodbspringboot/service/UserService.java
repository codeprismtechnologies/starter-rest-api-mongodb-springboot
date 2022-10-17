package starterrestapimongodbspringboot.service;

import org.springframework.data.domain.Page;
import starterrestapimongodbspringboot.dto.UserRequest;
import starterrestapimongodbspringboot.entity.User;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

public interface UserService {

    User signUp(UserRequest userRequest);
    User updateSignUp(UserRequest userRequest);
    User getUserById(String userId);
    Page<User> getAllUser(Integer pageNumber, Integer pageSize);
    String deleteUser(String userId);
}
