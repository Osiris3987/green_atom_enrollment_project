package com.example.green_atom_enrollment_project.web.mapper;

import com.example.green_atom_enrollment_project.domain.entity.user.User;
import com.example.green_atom_enrollment_project.web.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "entity.username", target = "username")
    UserDto toDto(User entity);

    @Mapping(source = "dto.username", target = "username")
    User toEntity(UserDto dto);
}
