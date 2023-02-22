package com.uptickTalent.NoteApp.repositories;

import com.uptickTalent.NoteApp.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
