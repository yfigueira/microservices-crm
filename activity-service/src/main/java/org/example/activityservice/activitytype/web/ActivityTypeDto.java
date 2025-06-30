package org.example.activityservice.activitytype.web;

import lombok.Builder;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.common.web.DtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Builder
public record ActivityTypeDto(
        Integer id,
        String type
) {
    @Mapper
    public interface ActivityTypeDtoMapper extends DtoMapper<ActivityType, ActivityTypeDto> {}

    public static ActivityTypeDtoMapper mapper() {
        return Mappers.getMapper(ActivityTypeDtoMapper.class);
    }
}
