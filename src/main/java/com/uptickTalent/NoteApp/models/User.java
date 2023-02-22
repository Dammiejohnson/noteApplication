package com.uptickTalent.NoteApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(unique = true, name = "email")
    @JsonProperty("email")
    private String email;
    @NotNull
    @JsonProperty("password")
    @Column(name = "password")
    private String password;

    @OneToMany(
            cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();


    public void addNote(Note note){
        notes.add(note);
    }

    @Override
    public String toString() {
        return String.format("id:%d\temail:%s", id,email);
    }
}