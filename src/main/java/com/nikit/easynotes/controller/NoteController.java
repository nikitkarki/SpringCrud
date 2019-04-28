package com.nikit.easynotes.controller;

import com.nikit.easynotes.model.Note;
import com.nikit.easynotes.request.NoteRequest;
import com.nikit.easynotes.resource.NoteResource;
import com.nikit.easynotes.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<NoteResource> getAllNotes(){
        log.info("Getting all notes . . .");
        return noteService.getAllNotes();
    }

    @PostMapping("/notes")
    public NoteResource createNote(@Valid @RequestBody NoteRequest noteRequest){
        return noteService.createNote(noteRequest);
    }

    @GetMapping("/notes/{id}")
    public NoteResource getNoteById(@PathVariable(value="id") Long noteId){
        return noteService.getNoteById(noteId);
    }

    @PutMapping("/notes/{id}")
    public NoteResource updateNote(@PathVariable(value = "id") Long noteId,
                                   @Valid @RequestBody Note noteDetails) {
        return noteService.updateNote(noteDetails,noteId);
    }
    @DeleteMapping("/notes/{id}")
    public NoteResource deleteNote(@PathVariable(value = "id") Long noteId) {
        return noteService.deleteNote(noteId);
    }

    @GetMapping("notes/{title}/title")
    public NoteResource findByTitle(@PathVariable(value = "title") String title){
        return noteService.findByTitle(title);
    }
}
