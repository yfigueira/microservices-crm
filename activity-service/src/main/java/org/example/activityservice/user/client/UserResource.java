package org.example.activityservice.user.client;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResource(
        UUID id,
        String firstName,
        String lastName
) {
}
