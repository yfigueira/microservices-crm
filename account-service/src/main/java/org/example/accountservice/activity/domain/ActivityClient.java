package org.example.accountservice.activity.domain;

import java.util.List;
import java.util.UUID;

public interface ActivityClient {

    List<Activity> getByContact(UUID contactId);
}
