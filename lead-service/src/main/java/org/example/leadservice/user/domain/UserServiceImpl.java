package org.example.leadservice.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    @Override
    public User getOwner(UUID id) {
        return null;
    }
}
