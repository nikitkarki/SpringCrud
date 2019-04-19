package com.nikit.easynotes.controller;

import com.nikit.easynotes.exception.ResourceNotFoundException;
import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.repository.NoteRepository;
import com.nikit.easynotes.resource.NoteResource;
import com.nikit.easynotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteRepository.findAll(new Sort(Sort.Direction.ASC,"title"));
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value="id") Long noteId){
        return noteRepository.findById(noteId) .orElseThrow(()-> new ResourceNotFoundException("Note","id",noteId));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

    @GetMapping("notes/{title}/title")
    public NoteResource findByTitle(@PathVariable(value = "title") String title){
        return noteService.findByTitle(title);
    }
}
