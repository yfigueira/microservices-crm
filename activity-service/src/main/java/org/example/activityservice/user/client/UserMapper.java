package org.example.activityservice.user.client;

import org.example.activityservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UserMapper {

    User toDomain(UserResource resource);
}
