package com.example.green_atom_enrollment_project.web.dto.message;

import com.example.green_atom_enrollment_project.web.dto.validation.OnCreate;
import com.example.green_atom_enrollment_project.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageDto {
    @Null(message = "message id must be null", groups = OnCreate.class)
    private UUID id;
    @NotNull(message = "message text must not be null", groups = OnCreate.class)
    private String text;
    @Null(message = "message author must be null", groups = {OnCreate.class, OnUpdate.class})
    private String author;

    @Null(message = "message author must be null", groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime created;
}
