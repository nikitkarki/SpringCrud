package com.nikit.easynotes.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikit.easynotes.model.Note;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class NoteResource {
    private Long id;
    private String title;
    private String content;
    @JsonProperty(value = "created_at")
    private Date createdAt;
    private String createdBy;
    private Note.Status status;
}
