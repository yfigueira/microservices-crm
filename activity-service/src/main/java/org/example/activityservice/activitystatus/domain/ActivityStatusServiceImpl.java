package org.example.activityservice.activitystatus.domain;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.exception.ActivityServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ActivityStatusServiceImpl implements ActivityStatusService {

    private final ActivityStatusRepository repository;

    @Override
    public List<ActivityStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public ActivityStatus getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> ActivityServiceException.notFound(ActivityStatus.class, id));
    }
}
