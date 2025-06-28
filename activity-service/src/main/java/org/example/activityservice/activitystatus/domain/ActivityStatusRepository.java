package org.example.activityservice.activitystatus.domain;

import java.util.List;
import java.util.Optional;

public interface ActivityStatusRepository {

    List<ActivityStatus> findAll();

    Optional<ActivityStatus> findById(int id);
}
