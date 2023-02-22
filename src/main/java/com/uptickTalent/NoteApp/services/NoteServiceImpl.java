package com.uptickTalent.NoteApp.services;


import com.uptickTalent.NoteApp.dtos.requests.NoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UpdateNoteRequest;
import com.uptickTalent.NoteApp.dtos.responses.UpdateResponse;
import com.uptickTalent.NoteApp.exceptions.NoteApplicationException;
import com.uptickTalent.NoteApp.models.Entry;
import com.uptickTalent.NoteApp.models.Note;
import com.uptickTalent.NoteApp.repositories.EntryRepository;
import com.uptickTalent.NoteApp.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final EntryRepository entryRepository;



    @Override
    public Note createNote(NoteDto noteDto) {
        Note note = Note.builder()
                    .title(noteDto.getTitle())
                    .entries(noteDto.getEntries())
                    .build();
        return noteRepository.save(note);
    }

    @Override
    public Note findNote(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new NoteApplicationException("Note with this id not found"));
    }

    @Override
    public String deleteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteApplicationException("Note with this id not found"));
        noteRepository.delete(note);
        return "Deleted Successfully";
    }

    @Override
    public UpdateResponse updateNote(Long id, UpdateNoteRequest request) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteApplicationException("Note with this id does not exist"));
        boolean isUpdate = false;

        if (!(request.getTitle()== null ||request.getTitle().trim().equals(""))){
            note.setTitle(request.getTitle());
            isUpdate = true;}

        if (!(request.getEntry()== null ||request.getTitle().trim().equals(""))){
            note.getEntries().add(request.getEntry());
            Optional<Entry> entry = entryRepository.findById(request.getEntry().getId());
            entry.ifPresent(value -> value.setLastUpdatedTime(LocalDateTime.now()));
            isUpdate = true;}

        if (isUpdate){
            noteRepository.save(note);
        }
        UpdateResponse response = new UpdateResponse();
        response.setMessage("Update is Successful");
        return response;
    }


}
