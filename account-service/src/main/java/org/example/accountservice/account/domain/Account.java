package org.example.accountservice.account.domain;

import lombok.Builder;
import org.example.accountservice.contact.domain.Contact;

import java.util.List;
import java.util.UUID;

@Builder
public record Account(
        UUID id,
        String name,
        String country,
        String city,
        String street,
        String streetNumber,
        String zipCode,
        String tin,
        String webUrl,
        List<Contact> contacts
) {
}
