package org.example.activityservice.activitytype.persistence;

import org.example.activityservice.activitytype.domain.ActivityType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ActivityTypeMapper {

    ActivityType toDomain(ActivityTypeEntity entity);
}
