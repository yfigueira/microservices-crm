package org.example.activityservice.activity.domain;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.activitystatus.domain.ActivityStatusService;
import org.example.activityservice.activitytype.domain.ActivityTypeService;
import org.example.activityservice.entitytype.domain.EntityTypeService;
import org.example.activityservice.exception.ActivityServiceException;
import org.example.activityservice.user.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;
    private final UserService userService;
    private final EntityTypeService entityTypeService;
    private final ActivityStatusService activityStatusService;
    private final ActivityTypeService activityTypeService;

    @Override
    public Activity create(Activity activity) {
        var scheduledActivity = ActivityScheduler.plan(activity);
        var created = repository.save(scheduledActivity);
        return fetchDetails(created);
    }

    @Override
    public Activity getById(UUID id) {
        return repository.findById(id)
                .map(this::fetchDetails)
                .orElseThrow(() -> ActivityServiceException.notFound(Activity.class, id));
    }

    @Override
    public List<Activity> getAll() {
        return repository.findAll().stream()
                .map(this::fetchDetails)
                .toList();
    }

    @Override
    public Activity update(UUID id, Activity activity) {
        if (!activity.id().equals(id)) {
            throw ActivityServiceException.actionNotAllowed(Activity.class, "id mismatch");
        }
        var updated = repository.update(id, activity);
        return fetchDetails(updated);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    private Activity fetchDetails(Activity activity) {
        return Optional.of(activity)
                .map(this::fetchOwner)
                .map(this::fetchEntityType)
                .map(this::fetchActivityStatus)
                .map(this::fetchActivityType)
                .orElse(activity);
    }

    private Activity fetchOwner(Activity activity) {
        var owner = userService.getOwner(activity.owner().id());
        return activity.withOwner(owner);
    }

    private Activity fetchEntityType(Activity activity) {
        var entityType = entityTypeService.getById(activity.entityType().id());
        return activity.withEntityType(entityType);
    }

    private Activity fetchActivityStatus(Activity activity) {
        var activityStatus = activityStatusService.getById(activity.status().id());
        return activity.withStatus(activityStatus);
    }

    private Activity fetchActivityType(Activity activity) {
        var activityType = activityTypeService.getById(activity.type().id());
        return activity.withType(activityType);
    }
}
