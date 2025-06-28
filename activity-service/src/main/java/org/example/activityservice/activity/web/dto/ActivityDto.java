package org.example.activityservice.activity.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.activityservice.activity.domain.Activity;
import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.common.web.DtoMapper;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ActivityDto(
        UUID id,
        @NotBlank(message = "Subject required")
        String subject,
        String description,
        LocalDateTime scheduledAt,
        LocalDateTime completedAt,
        @NotNull(message = "Activity type required")
        ActivityType type,
        ActivityStatus status,
        @NotNull(message = "Entity required")
        UUID entity,
        @NotNull(message = "Entity type required")
        EntityType entityType,
        @NotNull(message = "Owner required")
        User owner
) {
    @Mapper
    public interface ActivityDtoMapper extends DtoMapper<Activity, ActivityDto> {}

    public static ActivityDtoMapper mapper() {
        return Mappers.getMapper(ActivityDtoMapper.class);
    }
}
