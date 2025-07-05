package org.example.activityservice.user.web;

import lombok.Builder;
import org.example.activityservice.common.web.DtoMapper;
import org.example.activityservice.user.domain.User;
import org.mapstruct.Mapper;

import java.util.UUID;

@Builder
public record UserDto(
        UUID id,
        String firstName,
        String lastName
) {
    @Mapper
    public interface UserDtoMapper extends DtoMapper<User, UserDto> {}
}
