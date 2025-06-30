package org.example.activityservice.entitytype.persistence;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.entitytype.domain.EntityType;
import org.example.activityservice.entitytype.domain.EntityTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class EntityTypeDatabaseRepository implements EntityTypeRepository {

    private final EntityTypeMapper mapper;
    private final EntityTypeJpaRepository jpaRepository;

    @Override
    public List<EntityType> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<EntityType> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
