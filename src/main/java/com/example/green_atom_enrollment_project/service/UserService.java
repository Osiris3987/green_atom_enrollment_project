package com.example.green_atom_enrollment_project.service;

import com.example.green_atom_enrollment_project.domain.entity.user.User;

public interface UserService {
    User create(User user);

    User getById(Long id);

    User getByUsername(String username);
}
