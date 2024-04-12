package com.example.green_atom_enrollment_project.web.mapper;

import com.example.green_atom_enrollment_project.domain.entity.Message;
import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    @Autowired
    protected Updater updater;

    public abstract Message toEntity(MessageDto messageDto);

    public abstract MessageDto toDto(Message message);

    public abstract List<Message> toEntities(List<MessageDto> messageDtos);

    public abstract List<MessageDto> toDtos(List<Message> messages);

    public Message update(Message entity, MessageDto dto) {
        return updater.updateMessage(entity, dto);
    }

    @Mapper(
            componentModel = "spring",
            unmappedSourcePolicy = ReportingPolicy.ERROR,
            unmappedTargetPolicy = ReportingPolicy.IGNORE,
            typeConversionPolicy = ReportingPolicy.WARN,
            collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    interface Updater {
        Message updateMessage(@MappingTarget Message message, MessageDto changeDto);
    }
}
