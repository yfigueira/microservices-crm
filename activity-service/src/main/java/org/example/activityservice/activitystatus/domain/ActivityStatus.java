package org.example.activityservice.activitystatus.domain;

import lombok.Builder;

@Builder
public record ActivityStatus(
        int id,
        String status
) {
}
