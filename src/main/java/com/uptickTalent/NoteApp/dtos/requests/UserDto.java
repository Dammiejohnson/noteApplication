package com.uptickTalent.NoteApp.dtos.requests;

import com.uptickTalent.NoteApp.models.Note;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {
    private Long id;
    private String email;
    private List<Note> notes;
}
