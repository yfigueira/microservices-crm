package org.example.accountservice.contact.web;

import lombok.Builder;
import org.example.accountservice.common.web.DtoMapper;
import org.example.accountservice.contact.domain.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record ContactSummary(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
    @Mapper
    public interface ContactSummaryMapper extends DtoMapper<Contact, ContactSummary> {}

    public static ContactSummaryMapper mapper() {
        return Mappers.getMapper(ContactSummaryMapper.class);
    }
}
