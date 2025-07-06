package org.example.accountservice.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    List<Activity> getByContact(UUID contactId);
}
