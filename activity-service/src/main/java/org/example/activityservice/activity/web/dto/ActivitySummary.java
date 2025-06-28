package org.example.activityservice.activity.web.dto;

import lombok.Builder;
import org.example.activityservice.activity.domain.Activity;
import org.example.activityservice.common.web.DtoMapper;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ActivitySummary(
        UUID id,
        String subject,
        LocalDateTime scheduledAt,
        EntityType entityType,
        User owner
) {
    @Mapper
    public interface ActivitySummaryMapper extends DtoMapper<Activity, ActivitySummary> {}

    public static ActivitySummaryMapper mapper() {
        return Mappers.getMapper(ActivitySummaryMapper.class);
    }
}
