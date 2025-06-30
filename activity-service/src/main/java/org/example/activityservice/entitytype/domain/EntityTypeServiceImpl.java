package org.example.activityservice.entitytype.domain;

import lombok.RequiredArgsConstructor;
import org.example.activityservice.exception.ActivityServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class EntityTypeServiceImpl implements EntityTypeService {

    private final EntityTypeRepository repository;

    @Override
    public List<EntityType> getAll() {
        return repository.findAll();
    }

    @Override
    public EntityType getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> ActivityServiceException.notFound(EntityType.class, id));
    }
}
