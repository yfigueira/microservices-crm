package org.example.activityservice.activity.domain;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.exception.ActivityServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;

    @Override
    public Activity create(Activity activity) {
        var scheduledActivity = ActivityScheduler.plan(activity);
        return repository.save(scheduledActivity);
    }

    @Override
    public Activity getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> ActivityServiceException.notFound(Activity.class, id));
    }

    @Override
    public List<Activity> getAll() {
        return repository.findAll();
    }

    @Override
    public Activity update(UUID id, Activity activity) {
        if (!activity.id().equals(id)) {
            throw ActivityServiceException.actionNotAllowed(Activity.class, "id mismatch");
        }
        return repository.update(id, activity);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Activity> getByEntity(UUID entityId) {
        return repository.findByEntity(entityId);
    }
}
