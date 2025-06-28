package org.example.activityservice.activity.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.activityservice.activity.domain.ActivityService;
import org.example.activityservice.activity.web.dto.ActivityDto;
import org.example.activityservice.activity.web.dto.ActivitySummary;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ActivityController {

    private final ActivityService service;

    @PostMapping
    public ActivityDto create(@RequestBody @Valid ActivityDto request) {
        var newActivity = ActivityDto.mapper().toDomain(request);
        var createdActivity = service.create(newActivity);
        return ActivityDto.mapper().toDto(createdActivity);
    }

    @GetMapping("{id}")
    public ActivityDto getById(@PathVariable UUID id) {
        var activity = service.getById(id);
        return ActivityDto.mapper().toDto(activity);
    }

    @GetMapping
    public List<ActivitySummary> getAll() {
        return service.getAll().stream()
                .map(ActivitySummary.mapper()::toDto)
                .toList();
    }
}
