package org.example.leadservice.user.domain;

import java.util.UUID;

public interface UserService {

    User getOwner(UUID id);
}
