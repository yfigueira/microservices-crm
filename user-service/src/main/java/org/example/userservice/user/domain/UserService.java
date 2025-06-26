package org.example.userservice.user.domain;

import lombok.RequiredArgsConstructor;
import org.example.userservice.exception.UserServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient client;

    public User getById(UUID id) {
        return client.getById(id)
                .orElseThrow(() -> UserServiceException.notFound(User.class, id));
    }

    public List<User> getAll() {
        return client.getAll();
    }
}
