package org.example.accountservice.contact.persistence;

import org.example.accountservice.account.domain.Account;
import org.example.accountservice.contact.domain.Contact;
import org.example.accountservice.contact.domain.ContactPriority;
import org.example.accountservice.jobtitle.domain.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Arrays;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ContactMapper {

    Contact toDomain(ContactEntity entity);

    ContactEntity toEntity(Contact contact);

    default ContactPriority mapContactPriority(Integer entity) {
        return Arrays.stream(ContactPriority.values())
                .filter(v -> v.getCode().equals(entity))
                .findFirst()
                .orElse(ContactPriority.LOW);
    }

    default Integer mapContactPriority(ContactPriority domain) {
        return domain.getCode();
    }

    default JobTitle mapJobTitle(UUID entity) {
        return JobTitle.builder().id(entity).build();
    }

    default UUID mapJobTitle(JobTitle domain) {
        return domain.id();
    }

    default Account mapAccount(UUID entity) {
        return Account.builder().id(entity).build();
    }

    default UUID mapAccount(Account domain) {
        return domain.id();
    }
}
