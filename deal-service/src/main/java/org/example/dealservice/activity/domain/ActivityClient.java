package org.example.dealservice.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityClient {

    List<Activity> getByDeal(UUID dealId);
}
