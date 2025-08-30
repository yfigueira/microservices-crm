package org.example.accountservice.contact.domain;

import lombok.Builder;
import lombok.With;
import org.example.accountservice.account.domain.Account;
import org.example.accountservice.activity.domain.Activity;

import java.util.List;
import java.util.UUID;

@Builder
public record Contact(
        UUID id,
        String firstName,
        String lastName,
        String email,
        ContactPriority priority,
        @With Account company,
        UUID jobTitle,
        String phoneNumber,
        String privateEmail,
        String privatePhoneNumber,
        @With List<Activity> activities
) {
}
