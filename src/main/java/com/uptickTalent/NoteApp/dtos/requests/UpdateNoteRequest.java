package com.uptickTalent.NoteApp.dtos.requests;

import com.uptickTalent.NoteApp.models.Entry;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNoteRequest {
    private Long id;
    private String title;
    private Entry entry;
}
