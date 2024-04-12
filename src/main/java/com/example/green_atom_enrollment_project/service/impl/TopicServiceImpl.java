package com.example.green_atom_enrollment_project.service.impl;

import com.example.green_atom_enrollment_project.domain.entity.Topic;
import com.example.green_atom_enrollment_project.domain.exception.ResourceNotFoundException;
import com.example.green_atom_enrollment_project.repository.TopicRepository;
import com.example.green_atom_enrollment_project.service.MessageService;
import com.example.green_atom_enrollment_project.service.TopicService;
import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;
import com.example.green_atom_enrollment_project.web.mapper.TopicMapper;
import com.example.green_atom_enrollment_project.web.mapper.TopicWithMessagesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final MessageService messageService;
    private final TopicWithMessagesMapper topicWithMessagesMapper;
    private final TopicMapper topicMapper;

    @Override
    public TopicDto.TopicWithMessages create(TopicDto dto) {
        Topic topic = new Topic();
        topic.setName(dto.getName());
        topic.setMessages(new ArrayList<>());
        topic.setCreated(LocalDateTime.now());
        Topic topic1 = topicRepository.save(topic);
        topic1.getMessages().add(messageService.createWithNewTopic(topic1, dto.getMessageDtos().get(0)));
        return topicWithMessagesMapper.toDto(topic1);
    }

    @Override
    @Transactional
    public TopicDto.TopicWithMessages update(TopicDto.Topic dto) {
        Topic topic = topicRepository.findById(dto.id()).orElseThrow(()
                -> new ResourceNotFoundException("Topic not found")
        );
        Topic updated = topicMapper.updateTopic(topic, dto);
        return topicWithMessagesMapper.toDto(topicRepository.save(updated));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicDto.Topic> getAll(int offset, int limit) {
        return topicMapper.toDtos(topicRepository.findAll(PageRequest.of(offset, limit)).getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public TopicDto.TopicWithMessages getById(UUID id) {
        return topicWithMessagesMapper.toDto(
                topicRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Topic not found"))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> getMessagesByTopicId(UUID id) {
        return getById(id).messages();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        topicRepository.deleteById(id);
    }
}
