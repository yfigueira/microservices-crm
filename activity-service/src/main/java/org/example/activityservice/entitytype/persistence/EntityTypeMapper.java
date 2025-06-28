package org.example.activityservice.entitytype.persistence;

import org.example.activityservice.entitytype.domain.EntityType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface EntityTypeMapper {

    EntityType toDomain(EntityTypeEntity entity);
}
