package com.nikit.easynotes.converter;

import com.nikit.easynotes.model.Users;
import com.nikit.easynotes.resource.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserConverterImpl implements UserConverter{
    @Override
    public UserResource converterToUserResource(Users savedUser) {
      log.info("Converting user to user resource....");
      return UserResource.builder()
              .createdDate(savedUser.getCreatedAt())
              .status(savedUser.getStatus().name())
              .username(savedUser.getUsername())
              .build();
    }

    @Override
    public List<UserResource> convertAllUsers(List<Users> userList) {
        List<UserResource> userResourceList= new ArrayList<>();
        for(Users user:userList){
            userResourceList.add(converterToUserResource(user));
        }
        return userResourceList;
    }

}
