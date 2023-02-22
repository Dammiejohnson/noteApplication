package com.uptickTalent.NoteApp.dtos.responses;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String message;
    private boolean isSuccessful;
}
