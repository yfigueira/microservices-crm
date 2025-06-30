package org.example.activityservice.entitytype.domain;

import lombok.Builder;

@Builder
public record EntityType(
        Integer id,
        String type
) {
}
