package org.example.activityservice.exceptionhandler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Exception(
        String message,
        LocalDateTime timestamp
) {
}
