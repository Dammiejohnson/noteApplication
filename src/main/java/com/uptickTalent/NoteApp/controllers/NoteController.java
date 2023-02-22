package com.uptickTalent.NoteApp.controllers;

import com.uptickTalent.NoteApp.dtos.requests.NoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UpdateNoteRequest;
import com.uptickTalent.NoteApp.dtos.requests.UserRequest;
import com.uptickTalent.NoteApp.dtos.responses.LoginResponse;
import com.uptickTalent.NoteApp.dtos.responses.UpdateResponse;
import com.uptickTalent.NoteApp.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/noteApp")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/createNote")
    public ResponseEntity<?> createNote(@RequestBody NoteDto noteDto) {
        var response = noteService.createNote(noteDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getNote")
    public ResponseEntity<?> getNote(@RequestParam Long id) {
        var response = noteService.findNote(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateNote/{id}")
    public ResponseEntity<UpdateResponse> updateUser(@PathVariable Long id, @RequestBody UpdateNoteRequest request) {
        var status = noteService.updateNote(id, request );
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("deleteNote/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        String message = noteService.deleteById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
