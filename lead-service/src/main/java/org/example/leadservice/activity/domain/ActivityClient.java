package org.example.leadservice.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityClient {

    List<Activity> getByLead(UUID leadId);
}
