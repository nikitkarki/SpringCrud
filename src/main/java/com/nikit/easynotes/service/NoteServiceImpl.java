package com.nikit.easynotes.service;

import com.nikit.easynotes.exception.ResourceNotFoundException;
import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.repository.NoteRepository;
import com.nikit.easynotes.resource.NoteResource;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ToString
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public NoteResource findByTitle(String title) {
        return convertNoteToNoteResource(noteRepository.findByTitle(title), title);
    }

    private NoteResource convertNoteToNoteResource(Note note, String title) {
        if(note==null){
            throw new ResourceNotFoundException("Note","Title",title);
        }
        log.info("Found note is :: {}",note.toString());
        return NoteResource.builder()
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .id(note.getId())
                .title(note.getTitle())
                .build();
    }
}
