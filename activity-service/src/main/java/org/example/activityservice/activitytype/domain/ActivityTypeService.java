package org.example.activityservice.activitytype.domain;

import java.util.List;

public interface ActivityTypeService {

    List<ActivityType> getAll();

    ActivityType getById(Integer id);
}
