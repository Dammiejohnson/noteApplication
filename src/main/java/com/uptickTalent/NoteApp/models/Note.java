package com.uptickTalent.NoteApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    @Column(name = "note_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max= 255)
    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String title;


    @CreationTimestamp
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "note",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Entry> entries;

    @Override
    public String toString() {
        return String.format("id:%d\ttitle:%s", id, title);
    }

    public Note(String title) {
        this.title = title;
    }
}
