package org.example.userservice.user.web;

import lombok.RequiredArgsConstructor;
import org.example.userservice.user.domain.UserService;
import org.example.userservice.user.web.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("{id}")
    public UserDto getById(@PathVariable UUID id) {
        var user = service.getById(id);
        return UserDto.mapper().toDto(user);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll().stream()
                .map(UserDto.mapper()::toDto)
                .toList();
    }
}
