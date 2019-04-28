package com.nikit.easynotes.converter;

import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.resource.NoteResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class NoteConverterImpl implements NoteConverter {
    @Override
    public List<NoteResource> getAllNotes(List<Note> noteList) {
        List<NoteResource> noteResource= new ArrayList<>();
        for(Note note:noteList){
            noteResource.add(convertNoteToNoteResource(note));
        }
        return noteResource;
    }
    @Override
    public NoteResource convertNoteToNoteResource(Note note) {
        log.info("Converting note. . .");
        return NoteResource.builder()
                .title(note.getTitle())
                .createdAt(note.getCreatedAt())
                .content(note.getContent())
                .createdBy(note.getUsers().getUsername())
                .id(note.getId())
                .status(note.getStatus())
                .build();
    }
}
