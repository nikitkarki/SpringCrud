package com.nikit.easynotes.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequest {
    private String title;
    private String content;
    private Long userId;
}
