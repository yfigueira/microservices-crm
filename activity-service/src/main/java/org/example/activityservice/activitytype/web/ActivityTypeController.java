package org.example.activityservice.activitytype.web;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.activitytype.domain.ActivityTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity-types")
@RequiredArgsConstructor
public class ActivityTypeController {

    private final ActivityTypeService service;

    @GetMapping
    public List<ActivityTypeDto> getAll() {
        return service.getAll().stream()
                .map(ActivityTypeDto.mapper()::toDto)
                .toList();
    }
}
