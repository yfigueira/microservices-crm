package org.example.activityservice.activity.web.dto;

import lombok.Builder;
import org.example.activityservice.activity.domain.Activity;
import org.example.activityservice.activity.domain.ActivityType;
import org.example.activityservice.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Builder
public record ActivitySummary(
        UUID id,
        String subject,
        String description,
        Integer type,
        LocalDateTime scheduledAt
        ) {
    @Mapper
    public interface ActivitySummaryMapper extends DtoMapper<Activity, ActivitySummary> {

        default ActivityType mapActivityType(Integer dto) {
            return Arrays.stream(ActivityType.values())
                    .filter(v -> v.getCode().equals(dto))
                    .findFirst()
                    .orElse(ActivityType.NOT_AVAILABLE);
        }

        default Integer mapActivityType(ActivityType domain) {
            return domain.getCode();
        }
    }

    public static ActivitySummaryMapper mapper() {
        return Mappers.getMapper(ActivitySummaryMapper.class);
    }
}
