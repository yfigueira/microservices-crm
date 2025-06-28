package org.example.activityservice.activitytype.domain;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.exception.ActivityServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ActivityTypeServiceImpl implements ActivityTypeService {

    private final ActivityTypeRepository repository;

    @Override
    public List<ActivityType> getAll() {
        return repository.findAll();
    }

    @Override
    public ActivityType getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> ActivityServiceException.notFound(ActivityType.class, id));
    }
}
