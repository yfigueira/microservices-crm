package org.example.leadservice.activity.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Activity(
        UUID id,
        String subject,
        LocalDateTime scheduledAt,
        Integer type,
        Integer status,
        Integer entityType
) {
}
