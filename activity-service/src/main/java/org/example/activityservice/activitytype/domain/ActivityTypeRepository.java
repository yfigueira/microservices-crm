package org.example.activityservice.activitytype.domain;

import java.util.List;
import java.util.Optional;

public interface ActivityTypeRepository {

    List<ActivityType> findAll();

    Optional<ActivityType> findById(int id);
}
