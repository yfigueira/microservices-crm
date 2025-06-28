package org.example.activityservice.user.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record User(
        UUID id,
        String firstName,
        String lastName
) {

    public static User fallback() {
        return User.builder()
                .firstName("-")
                .lastName("-")
                .build();
    }
}
