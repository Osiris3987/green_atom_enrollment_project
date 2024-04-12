package com.example.green_atom_enrollment_project.web.controller;

import com.example.green_atom_enrollment_project.service.MessageService;
import com.example.green_atom_enrollment_project.service.TopicService;
import com.example.green_atom_enrollment_project.web.dto.message.MessageDto;
import com.example.green_atom_enrollment_project.web.dto.topic.TopicDto;
import com.example.green_atom_enrollment_project.web.dto.validation.OnCreate;
import com.example.green_atom_enrollment_project.web.dto.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topic")
public class TopicController {
    private final TopicService topicService;
    private final MessageService messageService;

    @GetMapping
    public List<TopicDto.Topic> getAll(
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "1") int limit
    ) {
        return topicService.getAll(offset, limit);
    }

    @GetMapping("/{id}")
    public TopicDto.TopicWithMessages getById(@PathVariable UUID id) {
        return topicService.getById(id);
    }

    @GetMapping("/{id}/messages")
    public List<MessageDto> getMessagesByTopicId(
            @PathVariable UUID id,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "1") int limit
    ) {
        return topicService.getMessagesByTopicId(id);
    }

    @PostMapping
    public TopicDto.TopicWithMessages create(
            @Validated(OnCreate.class) @RequestBody TopicDto dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        dto.getMessageDtos().get(0).setAuthor(userDetails.getUsername());
        return topicService.create(dto);
    }

    @PostMapping("/{topicId}/messages")
    public TopicDto.TopicWithMessages createMessage(
            @PathVariable UUID topicId,
            @Validated(OnCreate.class) @RequestBody MessageDto dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        dto.setAuthor(userDetails.getUsername());
        return messageService.create(topicId, dto);
    }

    @PutMapping
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public TopicDto.TopicWithMessages update(
            @Validated(OnUpdate.class) @RequestBody TopicDto.Topic dto
    ) {
        return topicService.update(dto);
    }

    @PutMapping("/{id}/messages")
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public TopicDto.TopicWithMessages updateMessageByTopicId(
            @PathVariable UUID id, @Validated(OnUpdate.class) @RequestBody MessageDto dto
    ) {
        return messageService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.hasAdminRole()")
    public void delete(@PathVariable UUID id) {
        topicService.delete(id);
    }

}
