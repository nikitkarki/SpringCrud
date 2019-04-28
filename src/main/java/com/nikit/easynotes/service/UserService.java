package com.nikit.easynotes.service;

import com.nikit.easynotes.model.Users;
import com.nikit.easynotes.request.UserRequest;
import com.nikit.easynotes.resource.UserResource;

import java.util.List;

public interface UserService {
    UserResource addNewUser(UserRequest userRequest);

    List<UserResource> getAllUsers();

    UserResource updateUser(UserRequest userRequest, Long userId);

    UserResource findUserById(Long userId);

    List<UserResource> findActiveUsers();

    List<UserResource> finInActiveUsers();

    Users deleteUser(Long userId);
}
