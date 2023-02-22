package com.uptickTalent.NoteApp.services;

import com.uptickTalent.NoteApp.dtos.requests.UserDto;
import com.uptickTalent.NoteApp.dtos.requests.UserRequest;
import com.uptickTalent.NoteApp.exceptions.NoteApplicationException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@RequiredArgsConstructor
class UserServiceImplTest {
    private final  UserService userService;
    private UserRequest request;

    @BeforeEach
    void setUp() {
        request = UserRequest.builder()
                .email("test2@gmail.com")
                .password("Damilola5$")
                .build();
    }

    @Test
    void testThatUserCanBeCreated(){
        UserDto userDto = userService.createUser(request);
        assertThat(userDto.getId(), is(notNullValue()));
    }

    @Test
    void testThatCreatingAUserWithARegisteredEmailThrowsException(){
        UserDto userDto = userService.createUser(request);
        assertThrows(NoteApplicationException.class, () -> userService.createUser(request));
    }

    @Test
    void testThatRegisteredUserCanBeFound(){
        UserDto userDto = userService.createUser(request);
        UserDto userDto1 = userService.findByEmail(userDto.getEmail());
        assertThat(userDto1.getEmail(), is("test2@gmail.com"));
    }


}