package com.example.green_atom_enrollment_project.web.mapper;

import com.example.green_atom_enrollment_project.domain.entity.Topic;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MessageMapper.class)
public interface NewTopicMapper {
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.messageDtos", target = "messages")
    Topic toEntity(TopicDto dto);

    TopicDto toDto(Topic dto);

}
