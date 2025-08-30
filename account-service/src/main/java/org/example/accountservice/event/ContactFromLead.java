package org.example.accountservice.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ContactFromLead(
        UUID leadId,
        UUID contactId
) {
}
