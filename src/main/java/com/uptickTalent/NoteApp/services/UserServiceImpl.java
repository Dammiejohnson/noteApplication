package com.uptickTalent.NoteApp.services;


import com.uptickTalent.NoteApp.dtos.requests.UserRequest;
import com.uptickTalent.NoteApp.dtos.requests.NoteDto;
import com.uptickTalent.NoteApp.dtos.requests.UserDto;
import com.uptickTalent.NoteApp.dtos.responses.LoginResponse;
import com.uptickTalent.NoteApp.dtos.responses.UpdateResponse;
import com.uptickTalent.NoteApp.exceptions.NoteApplicationException;
import com.uptickTalent.NoteApp.models.Note;
import com.uptickTalent.NoteApp.models.User;
import com.uptickTalent.NoteApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private  final NoteService noteService;


    @Override
    public UserDto createUser(UserRequest request) {
        Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail());
        if(userOptional.isEmpty()){
            String myHashedPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
            User user = User.builder()
                    .email(request.getEmail())
                    .password(myHashedPassword)
                    .build();

            user.setNotes(new ArrayList<>());
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        }
        throw new NoteApplicationException("User already exists");
    }

    @Override
    public LoginResponse login(UserRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(( )-> new NoteApplicationException("user account with email does not exists"));
        String myHashedLoginPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        boolean isValidUser = user.getPassword().equals(myHashedLoginPassword);
        LoginResponse response = new LoginResponse();
        if(isValidUser) {
            response.setMessage("login successful");
            response.setSuccessful(true);
        }
        else {
            response.setMessage("login not successful");
            response.setSuccessful(false);
        }
        return response;
    }

    @Override
    public Note addNote(Long userId, NoteDto noteDto) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new NoteApplicationException("User with this email not found"));
        Note note1 = noteService.createNote(noteDto);
        foundUser.addNote(note1);
        userRepository.save(foundUser);
        return note1;
    }

    @Override
    public UserDto findByEmail(String email) {
        User foundUser =userRepository.findUserByEmail(email).orElseThrow(() -> new NoteApplicationException("User with this email not found"));
        return UserDto.builder()
                .id(foundUser.getId())
                .email(foundUser.getEmail())
                .notes(foundUser.getNotes())
                .build();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public String deleteUserByEmail(String email) {
        User foundUser = userRepository.findUserByEmail(email).orElseThrow(() -> new NoteApplicationException("User with this email not found"));
        foundUser.getNotes().clear();
            userRepository.delete(foundUser);
            return "Deleted Successfully";
    }

    @Override
    public UpdateResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoteApplicationException("User with this id does not exist"));
        boolean isUpdate = false;

        if (!(request.getEmail()== null ||request.getEmail().trim().equals(""))){
            user.setEmail(request.getEmail());
            isUpdate = true;}

        if (!(request.getPassword()== null ||request.getPassword().trim().equals(""))){
            String myHashedPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
            user.setPassword(myHashedPassword);
            isUpdate = true;}

        if (isUpdate){
            userRepository.save(user);}
        UpdateResponse response = new UpdateResponse();
        response.setMessage("Update is Successful");
        return response;
    }
}
