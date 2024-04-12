package com.example.green_atom_enrollment_project.service.impl;

import com.example.green_atom_enrollment_project.domain.entity.user.Role;
import com.example.green_atom_enrollment_project.domain.entity.user.User;
import com.example.green_atom_enrollment_project.domain.exception.ResourceNotFoundException;
import com.example.green_atom_enrollment_project.repository.UserRepository;
import com.example.green_atom_enrollment_project.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(
            final User user
    ) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.USER);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PostConstruct
    private void insertUsersIntoDb() {
        createAdminUser();
        create(
                new User(
                        "Simple JohnDoe",
                        "johndoesimple@gmail.com",
                        "JohnDoeSimple",
                        Set.of(Role.ADMIN)
                )
        );
    }

    private void createAdminUser() {
        User user = new User();
        user.setName("John Doe Admin");
        user.setUsername("johndoeadmin@gmail.com");
        user.setPassword(passwordEncoder.encode("JohnDoeAdmin"));
        user.setRoles(Set.of(Role.ADMIN));
        userRepository.save(user);
    }
}
