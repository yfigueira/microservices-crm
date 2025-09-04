package org.example.dealservice.activity.web;

import lombok.Builder;
import org.example.dealservice.activity.domain.Activity;
import org.example.dealservice.common.web.DtoMapper;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ActivityDto(
        UUID id,
        String subject,
        LocalDateTime scheduledAt,
        Integer type,
        Integer Status,
        Integer entityType
) {
    @Mapper
    public interface ActivityDtoMapper extends DtoMapper<Activity, ActivityDto> {}
}
