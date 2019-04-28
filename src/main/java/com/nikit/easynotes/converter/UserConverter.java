package com.nikit.easynotes.converter;

import com.nikit.easynotes.model.Users;
import com.nikit.easynotes.resource.UserResource;

import java.util.List;

public interface UserConverter {
    UserResource converterToUserResource(Users savedUser);

    List<UserResource> convertAllUsers(List<Users> userList);

}
