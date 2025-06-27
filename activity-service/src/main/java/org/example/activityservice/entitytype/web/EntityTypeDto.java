package org.example.activityservice.entitytype.web;

import lombok.Builder;
import org.example.activityservice.common.web.DtoMapper;
import org.example.activityservice.entitytype.domain.EntityType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Builder
public record EntityTypeDto(
        int id,
        String type
) {
    @Mapper
    public interface EntityTypeDtoMapper extends DtoMapper<EntityType, EntityTypeDto> {}

    public static EntityTypeDtoMapper mapper() {
        return Mappers.getMapper(EntityTypeDtoMapper.class);
    }
}
