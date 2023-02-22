package com.uptickTalent.NoteApp.services;

import com.uptickTalent.NoteApp.dtos.requests.NoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UpdateNoteRequest;
import com.uptickTalent.NoteApp.dtos.responses.UpdateResponse;
import com.uptickTalent.NoteApp.models.Note;

public interface NoteService {
    Note createNote(NoteDto noteDto);
    Note findNote(Long id);
    String deleteById(Long id);
    UpdateResponse updateNote(Long id, UpdateNoteRequest request);

}
