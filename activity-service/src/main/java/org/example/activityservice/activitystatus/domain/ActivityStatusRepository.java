package org.example.activityservice.activitystatus.domain;

import java.util.List;

public interface ActivityStatusRepository {

    List<ActivityStatus> findAll();
}
