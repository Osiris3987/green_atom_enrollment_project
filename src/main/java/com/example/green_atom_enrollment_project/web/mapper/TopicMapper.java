package com.example.green_atom_enrollment_project.web.mapper;

import com.example.green_atom_enrollment_project.domain.entity.Topic;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TopicMapper {
    @Autowired
    protected Updater updater;

    public abstract List<TopicDto.Topic> toDtos(List<Topic> entities);

    public Topic updateTopic(Topic entity, TopicDto.Topic dto) {
        return updater.updateTopic(entity, dto);
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
        Topic updateTopic(@MappingTarget Topic entity, TopicDto.Topic dtoForUpdate);
    }
}
