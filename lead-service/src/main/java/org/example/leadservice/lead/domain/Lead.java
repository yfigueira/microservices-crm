package org.example.leadservice.lead.domain;

import lombok.Builder;
import lombok.With;
import org.example.leadservice.activity.domain.Activity;
import org.example.leadservice.company.domain.Company;
import org.example.leadservice.jobtitle.domain.JobTitle;
import org.example.leadservice.user.domain.User;

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
        LeadState state,
        Boolean isActive,
        @With User owner,
        @With JobTitle jobTitle,
        @With Company company,
        @With List<Activity> activities
) {
}
