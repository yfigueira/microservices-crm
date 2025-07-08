package org.example.leadservice.activity.client;

import org.example.leadservice.activity.domain.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ActivityMapper {

    Activity toDomain(ActivityResource resource);
}
