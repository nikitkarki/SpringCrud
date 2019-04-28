package com.nikit.easynotes.service;

import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.request.NoteRequest;
import com.nikit.easynotes.resource.NoteResource;

import java.util.List;

public interface NoteService {
    NoteResource findByTitle(String title);

    List<NoteResource> getAllNotes();

    NoteResource createNote(NoteRequest noteRequest);

    NoteResource updateNote(Note noteDetails, Long noteId);

    NoteResource deleteNote(Long noteId);

    NoteResource getNoteById(Long noteId);
}
