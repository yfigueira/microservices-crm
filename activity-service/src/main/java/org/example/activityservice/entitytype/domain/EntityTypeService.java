package org.example.activityservice.entitytype.domain;

import java.util.List;

public interface EntityTypeService {

    List<EntityType> getAll();

    EntityType getById(Integer id);
}
