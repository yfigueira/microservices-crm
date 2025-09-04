package org.example.dealservice.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    List<Activity> getByDeal(UUID dealId);
}
