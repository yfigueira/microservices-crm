package org.example.activityservice.activitytype.domain;

import lombok.Builder;

@Builder
public record ActivityType(
        int id,
        String type
) {
}
