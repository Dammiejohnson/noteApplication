package com.uptickTalent.NoteApp.repositories;

import com.uptickTalent.NoteApp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {
}
