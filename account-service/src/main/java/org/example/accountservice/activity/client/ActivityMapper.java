package org.example.accountservice.activity.client;

import org.example.accountservice.activity.domain.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {

    Activity toDomain(ActivityResource resource);
}
