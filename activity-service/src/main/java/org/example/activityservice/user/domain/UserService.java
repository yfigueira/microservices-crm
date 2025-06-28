package org.example.activityservice.user.domain;

import java.util.UUID;

public interface UserService {

    User getOwner(UUID id);
}
