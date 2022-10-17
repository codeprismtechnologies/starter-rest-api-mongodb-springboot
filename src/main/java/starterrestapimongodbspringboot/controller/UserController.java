package starterrestapimongodbspringboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starterrestapimongodbspringboot.dto.UserRequest;
import starterrestapimongodbspringboot.entity.User;
import starterrestapimongodbspringboot.service.UserService;

import javax.validation.Valid;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(tags = "User APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("This API will used to registered new user.")
    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody @Valid UserRequest userRequest){
        log.info("Entered into signUp(User controller) payLoad was :: {} ", userRequest);
        return new ResponseEntity<>(userService.signUp(userRequest), HttpStatus.CREATED);
    }

    @ApiOperation("This API will used to update user details. If exist else registered new user.")
    @PutMapping("/updateSignUp")
    public ResponseEntity<User> updateSignUp(@RequestBody @Valid UserRequest userRequest){
        log.info("Entered into updateSignUp(User controller) payLoad was :: {} ", userRequest);
        return new ResponseEntity<>(userService.updateSignUp(userRequest), HttpStatus.ACCEPTED);
    }

    @ApiOperation("This API will used to get user details by userId.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        log.info("Entered into getUser(User controller) userId was :: {} ", userId);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @ApiOperation("This API will used to findAll user details with pagination")
    @GetMapping(params = { "pageNumber", "pageSize" })
    public ResponseEntity<Page<User>> getAllUser(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize){
        log.info("Entered into getAllUser(User controller) pageNumber :: {} and pageSize :: {} ", pageNumber, pageSize);
        return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize), HttpStatus.OK);
    }

    @ApiOperation("This API will used delete user by userId.")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        log.info("Entered into deleteUser(User controller) userId was :: {} ", userId);
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.ACCEPTED);
    }
}
