package org.example.leadservice.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    List<Activity> getByLead(UUID leadId);
}
