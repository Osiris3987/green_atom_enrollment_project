package com.example.green_atom_enrollment_project.service;

import com.example.green_atom_enrollment_project.domain.entity.Message;
import com.example.green_atom_enrollment_project.domain.entity.Topic;
import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;

import java.util.UUID;

public interface MessageService {
    Message createWithNewTopic(Topic topic, MessageDto dto);

    TopicDto.TopicWithMessages create(UUID topicId, MessageDto dto);

    TopicDto.TopicWithMessages update(UUID topicId, MessageDto dto);

    void delete(UUID id);
}
