package com.nikit.easynotes.service;

import com.nikit.easynotes.converter.UserConverter;
import com.nikit.easynotes.exception.ResourceNotFoundException;
import com.nikit.easynotes.model.UserStatus;
import com.nikit.easynotes.model.Users;
import com.nikit.easynotes.repository.UserRepository;
import com.nikit.easynotes.request.UserRequest;
import com.nikit.easynotes.resource.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserConverter userConverter;


    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserResource addNewUser(UserRequest userRequest) {
        log.info("Adding new user with request::{}", userRequest.toString());
        Users users = new Users();
        users.setUsername(userRequest.getUsername());
        users.setPassword("test123");
        users.setStatus(UserStatus.ACTIVE);
        Users savedUser = userRepository.save(users);
        return userConverter.converterToUserResource(savedUser);
    }

    @Override
    public List<UserResource> getAllUsers() {
        List<Users> userList = userRepository.findAll();
        return userConverter.convertAllUsers(userList);
    }

    @Override
    public UserResource updateUser(UserRequest userRequest, Long userId) {
        Users userResource = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));;
        userResource.setUsername(userRequest.getUsername());
        userResource.setStatus(UserStatus.valueOf(userRequest.getStatus()));
        Users updateUser = userRepository.save(userResource);
        return userConverter.converterToUserResource(updateUser);
    }

    @Override
    public UserResource findUserById(Long userId) {
        return getUser(userRepository.findUserById(userId),userId);
    }

    @Override
    public List<UserResource> findActiveUsers() {
        List<Users> usersList= userRepository.findActiveUsers();
        return userConverter.convertAllUsers(usersList);
    }

    @Override
    public List<UserResource> finInActiveUsers() {
        List<Users> usersList= userRepository.findInActiveUsers();
        return userConverter.convertAllUsers(usersList);
    }

    @Override
    public Users deleteUser(Long userId) {
        Users users= userRepository.findById(userId) .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        users.setStatus(UserStatus.DELETE);
        return userRepository.save(users);
    }

    private UserResource getUser(Users user, Long userId) {
        if(user==null){
            throw new ResourceNotFoundException("User","id",userId);
        }
        log.info("Found user is:: {}",userId);
        return UserResource.builder()
                .username(user.getUsername())
                .status(user.getStatus().name())
                .createdDate(user.getCreatedAt())
                .build();
    }
}
