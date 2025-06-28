package org.example.activityservice.activity.web.dto;

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
        String subject,
        String description,
        LocalDateTime scheduledAt,
        LocalDateTime completedAt,
        ActivityType type,
        ActivityStatus status,
        UUID entity,
        EntityType entityType,
        User owner
) {
    @Mapper
    public interface ActivityDtoMapper extends DtoMapper<Activity, ActivityDto> {}

    public static ActivityDtoMapper mapper() {
        return Mappers.getMapper(ActivityDtoMapper.class);
    }
}
