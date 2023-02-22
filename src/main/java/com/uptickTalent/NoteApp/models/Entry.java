package com.uptickTalent.NoteApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Entry {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String text;

    @CreationTimestamp
    private LocalDateTime entryTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    public Entry(String text) {
        this.text = text;
    }
}
