package org.example.accountservice.jobtitle.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JobTitle(
        UUID id,
        String name
) {
}
