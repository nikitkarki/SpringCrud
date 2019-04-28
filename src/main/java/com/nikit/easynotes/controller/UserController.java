package com.nikit.easynotes.controller;

import com.nikit.easynotes.model.Users;
import com.nikit.easynotes.repository.UserRepository;
import com.nikit.easynotes.request.UserRequest;
import com.nikit.easynotes.resource.UserResource;
import com.nikit.easynotes.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository, UserRepository userRepository1) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @PostMapping(value = "/users")
    public UserResource addNewUser(@RequestBody UserRequest userRequest){
        return userService.addNewUser(userRequest);
    }
    @GetMapping(value = "/users")
    public List<UserResource> getAllUsers(){
        return userService.getAllUsers();
    }
    @PutMapping(value = "/users/{user_id}")
    public UserResource updateUser(@PathVariable(value = "user_id") Long userId,
                                   @RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest, userId);
    }
    @GetMapping(value = "/users/{userId}")
    public UserResource findUserById(@PathVariable(value = "userId") Long userId){
        return userService.findUserById(userId);
    }
    @GetMapping(value = "/users/active")
    public List<UserResource> findActiveUsers(){
        return userService.findActiveUsers();
    }
    @GetMapping(value = "/users/inactive")
    public List<UserResource> findInActiveUsers(){
        return userService.finInActiveUsers();
    }
    @DeleteMapping("/users/{userId}")
    public Users deleteUser(@PathVariable(value = "userId") Long userId){
        return userService.deleteUser(userId);
    }
}
