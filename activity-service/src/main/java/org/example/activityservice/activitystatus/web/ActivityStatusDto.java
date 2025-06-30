package org.example.activityservice.activitystatus.web;

import lombok.Builder;
import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Builder
public record ActivityStatusDto(
        Integer id,
        String status
) {
    @Mapper
    public interface ActivityStatusDtoMapper extends DtoMapper<ActivityStatus, ActivityStatusDto> {}

    public static ActivityStatusDtoMapper mapper() {
        return Mappers.getMapper(ActivityStatusDtoMapper.class);
    }
}
