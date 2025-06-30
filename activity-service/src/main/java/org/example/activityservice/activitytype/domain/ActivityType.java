package org.example.activityservice.activitytype.domain;

import lombok.Builder;

@Builder
public record ActivityType(
        Integer id,
        String type
) {
}
