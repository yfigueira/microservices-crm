package org.example.leadservice.user.web;

import lombok.Builder;
import org.example.leadservice.common.web.DtoMapper;
import org.example.leadservice.user.domain.User;
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
