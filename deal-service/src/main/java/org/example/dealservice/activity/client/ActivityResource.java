package org.example.dealservice.activity.client;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ActivityResource(
        UUID id,
        String subject,
        String description,
        Integer type,
        LocalDateTime scheduledAt
) {
}
