package org.example.activityservice.activity.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository {

    Activity save(Activity activity);

    Optional<Activity> findById(UUID id);

    List<Activity> findAll();

    Activity update(UUID id, Activity activity);

    void delete(UUID id);
}
