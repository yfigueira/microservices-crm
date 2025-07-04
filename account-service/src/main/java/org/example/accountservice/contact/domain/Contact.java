package org.example.accountservice.contact.domain;

import lombok.Builder;
import org.example.accountservice.account.domain.Account;
import org.example.accountservice.jobtitle.domain.JobTitle;

import java.util.UUID;

@Builder
public record Contact(
        UUID id,
        String firstName,
        String lastName,
        String email,
        ContactPriority priority,
        Account company,
        JobTitle jobTitle,
        String phoneNumber,
        String privateEmail,
        String privatePhoneNumber
) {
}
