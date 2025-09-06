package org.example.dealservice.activity.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Activity(
        UUID id,
        String subject,
        String description,
        Integer type,
        LocalDateTime scheduledAt
) {
}
