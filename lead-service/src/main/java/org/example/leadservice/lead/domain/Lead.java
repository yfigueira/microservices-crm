package org.example.leadservice.lead.domain;

import lombok.Builder;
import lombok.With;
import org.example.leadservice.activity.domain.Activity;

import java.util.List;
import java.util.UUID;

@Builder
public record Lead(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String subject,
        String city,
        @With LeadState state,
        @With Boolean isActive,
        UUID owner,
        UUID jobTitle,
        UUID company,
        @With List<Activity> activities
) {
}
