package org.example.activityservice.activitystatus.web;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.activitystatus.domain.ActivityStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity-statuses")
@RequiredArgsConstructor
public class ActivityStatusController {

    private final ActivityStatusService service;

    @GetMapping
    public List<ActivityStatusDto> getAll() {
        return service.getAll().stream()
                .map(ActivityStatusDto.mapper()::toDto)
                .toList();
    }
}
