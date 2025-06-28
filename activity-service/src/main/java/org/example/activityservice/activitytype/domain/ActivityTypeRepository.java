package org.example.activityservice.activitytype.domain;

import java.util.List;

public interface ActivityTypeRepository {

    List<ActivityType> findAll();
}
