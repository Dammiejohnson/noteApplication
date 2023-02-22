package com.uptickTalent.NoteApp.dtos.requests;

import com.uptickTalent.NoteApp.models.Entry;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddNoteDto {
    private String title;
    private List<Entry> entries;
}
