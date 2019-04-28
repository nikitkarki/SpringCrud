package com.nikit.easynotes.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Entity
@Table(name="notes")
@Getter
@Setter
@ToString
public class Note extends AbstractEntity implements Serializable {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private Status status;

    public enum Status{
        ACTIVE, INACTIVE, DELETE
    }


}
