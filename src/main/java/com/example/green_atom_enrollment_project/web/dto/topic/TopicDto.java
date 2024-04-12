package com.example.green_atom_enrollment_project.web.dto.topic;

import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import com.example.green_atom_enrollment_project.web.dto.validation.OnCreate;
import com.example.green_atom_enrollment_project.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class TopicDto {
    @Null(message = "topic id must be null", groups = OnCreate.class)
    private UUID id;
    @NotNull(message = "topic name must not be null", groups = OnCreate.class)
    private String name;
    private List<MessageDto> messageDtos;
    private MessageDto message;

    public TopicDto(
            String name,
            List<MessageDto> messageDtos,
            MessageDto message
    ) {
        this.name = name;
        this.messageDtos = List.of(message);
        this.message = message;

    }

    public record Topic(
            @NotNull(message = "topic id must not be null", groups = OnUpdate.class)
            UUID id,
            String name
    ) {
    }

    public record TopicWithMessages(
            UUID id,
            String name,
            LocalDateTime created,
            List<MessageDto> messages
    ) {
    }
}
