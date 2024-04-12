package com.example.green_atom_enrollment_project.service;

import com.example.green_atom_enrollment_project.web.dto.auth.JwtRequest;
import com.example.green_atom_enrollment_project.web.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);


}
