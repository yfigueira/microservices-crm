package org.example.activityservice.activitystatus.persistence;

import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ActivityStatusMapper {

    ActivityStatus toDomain(ActivityStatusEntity entity);
}
