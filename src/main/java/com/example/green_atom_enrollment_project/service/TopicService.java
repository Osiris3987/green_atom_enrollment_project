package com.example.green_atom_enrollment_project.service;

import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    TopicDto.TopicWithMessages create(TopicDto dto);

    TopicDto.TopicWithMessages update(TopicDto.Topic dto);

    List<TopicDto.Topic> getAll(int offset, int limit);

    TopicDto.TopicWithMessages getById(UUID id);

    List<MessageDto> getMessagesByTopicId(UUID id);

    void delete(UUID id);
}
