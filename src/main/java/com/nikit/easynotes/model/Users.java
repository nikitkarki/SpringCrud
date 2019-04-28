package com.nikit.easynotes.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class Users extends AbstractEntity implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private UserStatus status;

}
