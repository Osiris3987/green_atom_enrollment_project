package com.example.green_atom_enrollment_project.web.dto.user;

import com.example.green_atom_enrollment_project.web.dto.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserDto {
    private Long id;
    @NotNull(
            message = "Name must be not null.",
            groups = {OnCreate.class}
    )
    @Length(
            max = 255,
            message = "Name length must be smaller than 255 symbols.",
            groups = {OnCreate.class}
    )
    private String name;

    @NotNull(
            message = "Username must be not null.",
            groups = {OnCreate.class}
    )
    @Length(
            max = 255,
            message = "Username length must be smaller than 255 symbols.",
            groups = {OnCreate.class}
    )
    @Email(message = "Username must be a valid email")
    private String username;

    @JsonProperty(
            access = JsonProperty.Access.WRITE_ONLY
    )
    @NotNull(
            message = "Password must be not null.",
            groups = {OnCreate.class}
    )
    private String password;

}
