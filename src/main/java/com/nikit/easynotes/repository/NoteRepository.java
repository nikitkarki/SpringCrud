package com.nikit.easynotes.repository;

import com.nikit.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    @Query("SELECT n from Note n where n.title=?1")
    Note findByTitle(String title);
}
