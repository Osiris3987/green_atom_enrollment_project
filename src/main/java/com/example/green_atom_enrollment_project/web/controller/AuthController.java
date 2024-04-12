package com.example.green_atom_enrollment_project.web.controller;

import com.example.green_atom_enrollment_project.domain.entity.user.User;
import com.example.green_atom_enrollment_project.service.AuthService;
import com.example.green_atom_enrollment_project.service.UserService;
import com.example.green_atom_enrollment_project.web.dto.auth.JwtRequest;
import com.example.green_atom_enrollment_project.web.dto.auth.JwtResponse;
import com.example.green_atom_enrollment_project.web.dto.user.UserDto;
import com.example.green_atom_enrollment_project.web.dto.validation.OnCreate;
import com.example.green_atom_enrollment_project.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Auth Controller",
        description = "Auth API"
)
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(
            @Validated @RequestBody final JwtRequest loginRequest
    ) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(
            @Validated(OnCreate.class)
            @RequestBody final UserDto userDto
    ) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(
            @RequestBody final String refreshToken
    ) {
        return authService.refresh(refreshToken);
    }

}
