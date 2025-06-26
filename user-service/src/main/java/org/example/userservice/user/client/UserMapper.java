package org.example.userservice.user.client;

import org.example.userservice.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toDomain(UserResource resource);
}
