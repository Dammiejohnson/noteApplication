package com.uptickTalent.NoteApp.controllers;

import com.uptickTalent.NoteApp.dtos.requests.AddNoteDto;
import com.uptickTalent.NoteApp.dtos.requests.NoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UserRequest;
import com.uptickTalent.NoteApp.dtos.requests.UserDto;
import com.uptickTalent.NoteApp.dtos.responses.APIResponse;
import com.uptickTalent.NoteApp.dtos.responses.LoginResponse;
import com.uptickTalent.NoteApp.dtos.responses.UpdateResponse;
import com.uptickTalent.NoteApp.exceptions.NoteApplicationException;
import com.uptickTalent.NoteApp.models.Note;
import com.uptickTalent.NoteApp.models.User;
import com.uptickTalent.NoteApp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/noteApp")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) {
        try {
            APIResponse response = APIResponse.builder()
                    .payload(userService.createUser(request))
                    .message("User created successfully")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (NoteApplicationException ex) {
            APIResponse response = APIResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest request) {
       var feedback = userService.login(request);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }


    @PutMapping("/addNote/{userId}")
    public ResponseEntity<Note> addNote(@PathVariable Long userId, @RequestBody NoteDto addNoteDto) {
        var feedBack = userService.addNote(userId, addNoteDto);
        return new ResponseEntity<>(feedBack, HttpStatus.OK);
    }



        @GetMapping("/findUser")
    public ResponseEntity<UserDto> findUser(@RequestParam String email) {
        var userFound = userService.findByEmail(email);
        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<?> findAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        APIResponse apiResponse = APIResponse.builder()
                .payload(users)
                .message("users returned successfully")
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/updateUser/{id}")
    public ResponseEntity<UpdateResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        var status = userService.updateUser(id, request);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        String message = userService.deleteUserByEmail(email);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
