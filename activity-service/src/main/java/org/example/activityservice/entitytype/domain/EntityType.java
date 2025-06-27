package org.example.activityservice.entitytype.domain;

import lombok.Builder;

@Builder
public record EntityType(
        int id,
        String type
) {
}
