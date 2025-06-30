package org.example.activityservice.entitytype.domain;

import java.util.List;
import java.util.Optional;

public interface EntityTypeRepository {

    List<EntityType> findAll();

    Optional<EntityType> findById(Integer id);
}
