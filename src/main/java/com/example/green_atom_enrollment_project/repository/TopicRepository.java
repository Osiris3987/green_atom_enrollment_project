package com.example.green_atom_enrollment_project.repository;

import com.example.green_atom_enrollment_project.domain.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
