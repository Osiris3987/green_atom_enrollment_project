package com.example.green_atom_enrollment_project.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    private String author;

    private LocalDateTime created;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
