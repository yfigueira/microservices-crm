package org.example.userservice.exceptionhandler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Exception(
        String message,
        LocalDateTime timestamp
) {
}
