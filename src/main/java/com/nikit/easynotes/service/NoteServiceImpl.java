package com.nikit.easynotes.service;

import com.nikit.easynotes.converter.NoteConverter;
import com.nikit.easynotes.converter.UserConverter;
import com.nikit.easynotes.exception.ResourceNotFoundException;
import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.model.Users;
import com.nikit.easynotes.repository.NoteRepository;
import com.nikit.easynotes.repository.UserRepository;
import com.nikit.easynotes.request.NoteRequest;
import com.nikit.easynotes.resource.NoteResource;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@ToString
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteConverter noteConverter;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserConverter userConverter, NoteConverter noteConverter, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.noteConverter = noteConverter;
        this.userRepository = userRepository;
    }

    @Override
    public NoteResource findByTitle(String title) {
        return noteConverter.convertNoteToNoteResource(noteRepository.findByTitle(title));
    }

    @Override
    public List<NoteResource> getAllNotes() {
        List<Note> noteList = noteRepository.findAll();
        return noteConverter.getAllNotes(noteList);
    }

    @Override
    public NoteResource createNote(NoteRequest noteRequest) {
        Users users= userRepository.findActiveUser(noteRequest.getUserId());
        if (users == null) {
            throw new ResourceNotFoundException("User", "id", noteRequest.getUserId());
        }
        Note note= new Note();
        note.setContent(noteRequest.getContent());
        note.setTitle(noteRequest.getTitle());
        note.setUsers(users);
        note.setStatus(Note.Status.ACTIVE);
        return noteConverter.convertNoteToNoteResource(noteRepository.save(note));
    }

    @Override
    public NoteResource updateNote(Note noteDetails, Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setStatus(noteDetails.getStatus());
        Note updatedNote = noteRepository.save(note);
        return noteConverter.convertNoteToNoteResource(updatedNote);
    }

    @Override
    public NoteResource deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        note.setStatus(Note.Status.DELETE);
        Note updatedNote = noteRepository.save(note);
        return noteConverter.convertNoteToNoteResource(updatedNote);
    }

    @Override
    public NoteResource getNoteById(Long noteId) {
        Note note= noteRepository.findById(noteId)
                .orElseThrow(()-> new ResourceNotFoundException("Note","id",noteId));
        return noteConverter.convertNoteToNoteResource(note);
    }
}
