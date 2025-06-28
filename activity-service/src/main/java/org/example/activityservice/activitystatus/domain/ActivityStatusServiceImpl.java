package org.example.activityservice.activitystatus.domain;

import lombok.RequiredArgsConstructor;
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
}
