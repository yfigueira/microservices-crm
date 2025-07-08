package org.example.leadservice.lead.domain;

import lombok.Builder;
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
        User owner,
        JobTitle jobTitle,
        Company company,
        List<Activity> activities
) {
}
