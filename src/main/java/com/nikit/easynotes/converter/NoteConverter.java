package com.nikit.easynotes.converter;

import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.resource.NoteResource;

import java.util.List;

public interface NoteConverter {
    List<NoteResource> getAllNotes(List<Note> noteList);

    NoteResource convertNoteToNoteResource(Note note);
}
