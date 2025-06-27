package org.example.activityservice.entitytype.web;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.entitytype.domain.EntityTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entity-types")
@RequiredArgsConstructor
public class EntityTypeController {

    private final EntityTypeService service;

    @GetMapping
    public List<EntityTypeDto> getAll() {
        return service.getAll().stream()
                .map(EntityTypeDto.mapper()::toDto)
                .toList();
    }
}
