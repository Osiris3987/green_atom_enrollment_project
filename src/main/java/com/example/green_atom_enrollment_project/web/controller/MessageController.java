package com.example.green_atom_enrollment_project.web.controller;

import com.example.green_atom_enrollment_project.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;

    @DeleteMapping("/{messageId}")
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public void deleteMessage(@PathVariable UUID messageId) {
        messageService.delete(messageId);
    }

}
