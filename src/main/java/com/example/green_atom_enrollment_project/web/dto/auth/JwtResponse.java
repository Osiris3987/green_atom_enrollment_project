package com.example.green_atom_enrollment_project.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtResponse {
    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}
