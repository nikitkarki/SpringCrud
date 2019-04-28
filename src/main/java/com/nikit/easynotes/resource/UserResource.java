package com.nikit.easynotes.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserResource {
    private String username;
    private String status;
    private Date createdDate;
}
