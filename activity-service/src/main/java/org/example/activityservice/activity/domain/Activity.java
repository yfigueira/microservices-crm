package org.example.activityservice.activity.domain;

import lombok.Builder;
import lombok.With;
import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.user.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Activity(
        UUID id,
        String subject,
        String description,
        @With LocalDateTime scheduledAt,
        LocalDateTime completedAt,
        @With ActivityType type,
        @With ActivityStatus status,
        UUID entity,
        @With EntityType entityType,
        @With User owner,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
