package com.uptickTalent.NoteApp.services;

import com.uptickTalent.NoteApp.dtos.requests.AddNoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UserRequest;
import com.uptickTalent.NoteApp.dtos.requests.NoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UserDto;
import com.uptickTalent.NoteApp.dtos.responses.LoginResponse;
import com.uptickTalent.NoteApp.dtos.responses.UpdateResponse;
import com.uptickTalent.NoteApp.models.Note;
import com.uptickTalent.NoteApp.models.User;

import java.util.List;


public interface UserService {
 UserDto createUser(UserRequest request);
 LoginResponse login(UserRequest request);
 UserDto findByEmail(String email);
 String deleteUserByEmail(String email);
 Note addNote(Long userId, NoteDto note);
 List<UserDto> getAllUsers();
 UpdateResponse updateUser(Long id, UserRequest request);

}

