package org.example.leadservice.user.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserClient {

    Optional<User> getOwner(UUID id);
}
