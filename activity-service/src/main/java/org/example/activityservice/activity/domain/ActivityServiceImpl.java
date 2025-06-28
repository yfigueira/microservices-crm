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
        return repository.save(activity);
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
}
