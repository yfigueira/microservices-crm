package org.example.activityservice.activitystatus.persistence;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.activitystatus.domain.ActivityStatus;
import org.example.activityservice.activitystatus.domain.ActivityStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ActivityStatusDatabaseRepository implements ActivityStatusRepository {

    private final ActivityStatusJpaRepository jpaRepository;
    private final ActivityStatusMapper mapper;

    @Override
    public List<ActivityStatus> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ActivityStatus> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
