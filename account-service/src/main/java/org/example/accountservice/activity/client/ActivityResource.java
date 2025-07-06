package org.example.accountservice.activity.client;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ActivityResource(
        UUID id,
        String subject,
        LocalDateTime scheduledAt,
        Integer type,
        Integer status,
        Integer entityType
) {
}
