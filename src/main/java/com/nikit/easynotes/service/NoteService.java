package com.nikit.easynotes.service;

import com.nikit.easynotes.resource.NoteResource;

public interface NoteService {
    NoteResource findByTitle(String title);
}
