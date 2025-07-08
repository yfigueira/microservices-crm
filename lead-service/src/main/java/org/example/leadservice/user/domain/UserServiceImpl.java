package org.example.leadservice.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserClient client;

    @Override
    public User getOwner(UUID id) {
        return client.getOwner(id).orElse(User.fallback());
    }
}
