package org.example.leadservice.user.client;

import org.example.leadservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface UserMapper {

    User toDomain(UserResource resource);
}
