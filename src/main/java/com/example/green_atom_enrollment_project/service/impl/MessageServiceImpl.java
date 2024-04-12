package com.example.green_atom_enrollment_project.service.impl;

import com.example.green_atom_enrollment_project.domain.entity.Message;
import com.example.green_atom_enrollment_project.domain.entity.Topic;
import com.example.green_atom_enrollment_project.domain.exception.ResourceNotFoundException;
import com.example.green_atom_enrollment_project.repository.MessageRepository;
import com.example.green_atom_enrollment_project.repository.TopicRepository;
import com.example.green_atom_enrollment_project.service.MessageService;
import com.example.green_atom_enrollment_project.service.TopicService;
import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;
import com.example.green_atom_enrollment_project.web.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final TopicRepository topicRepository;
    private final MessageMapper messageMapper;
    private TopicService topicService;

    @Autowired
    public void setTopicService(@Lazy TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public Message createWithNewTopic(Topic topic, MessageDto dto) {
        Message message = new Message();
        message.setCreated(LocalDateTime.now());
        message.setText(dto.getText());
        message.setTopic(topic);
        message.setAuthor(dto.getAuthor());
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public TopicDto.TopicWithMessages update(UUID topicId, MessageDto dto) {
        topicService.getMessagesByTopicId(topicId)
                .stream()
                .filter(d -> d.getId().equals(dto.getId()))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        Message forUpdate = messageRepository.findById(dto.getId()).orElseThrow();
        Message message = messageMapper.update(forUpdate, dto);
        messageRepository.save(message);
        return topicService.getById(topicId);
    }

    @Override
    public TopicDto.TopicWithMessages create(UUID topicId, MessageDto dto) {
        Message message = messageMapper.toEntity(dto);
        message.setTopic(topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Topic not found")));
        message.setCreated(LocalDateTime.now());
        messageRepository.save(message);
        return topicService.getById(topicId);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        messageRepository.deleteById(id);
    }
}
