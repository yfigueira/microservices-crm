package org.example.activityservice.activitytype.persistence;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.activitytype.domain.ActivityType;
import org.example.activityservice.activitytype.domain.ActivityTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class ActivityTypeDatabaseRepository implements ActivityTypeRepository {

    private final ActivityTypeJpaRepository jpaRepository;
    private final ActivityTypeMapper mapper;

    @Override
    public List<ActivityType> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
