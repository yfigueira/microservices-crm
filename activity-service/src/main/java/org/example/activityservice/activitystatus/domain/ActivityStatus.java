package org.example.activityservice.activitystatus.domain;

import lombok.Builder;

@Builder
public record ActivityStatus(
        Integer id,
        String status
) {
}
