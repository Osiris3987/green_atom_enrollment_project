package com.example.green_atom_enrollment_project.web.mapper;

import com.example.green_atom_enrollment_project.domain.entity.Topic;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = MessageMapper.class)
public interface TopicWithMessagesMapper {
    @Mapping(source = "dto.messages", target = "messages")
    Topic toEntity(TopicDto.TopicWithMessages dto);

    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.messages", target = "messages")
    TopicDto.TopicWithMessages toDto(Topic entity);

}
