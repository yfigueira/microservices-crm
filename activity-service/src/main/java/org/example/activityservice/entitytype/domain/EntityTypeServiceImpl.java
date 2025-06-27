package org.example.activityservice.entitytype.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityTypeServiceImpl implements EntityTypeService {

    private final EntityTypeRepository repository;

    @Override
    public List<EntityType> getAll() {
        return repository.findAll();
    }
}
