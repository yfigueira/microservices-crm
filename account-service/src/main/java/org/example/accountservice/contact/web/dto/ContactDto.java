package org.example.accountservice.contact.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.example.accountservice.common.web.DtoMapper;
import org.example.accountservice.contact.domain.Contact;
import org.example.accountservice.jobtitle.web.JobTitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Builder
public record ContactDto(
        UUID id,
        @NotBlank(message = "First name required")
        String firstName,
        @NotBlank(message = "Last name required")
        String lastName,
        @NotBlank(message = "Email required")
        @Email(message = "Email format not valid")
        String email,
        Integer priority,
        JobTitleDto jobTitle,
        String phoneNumber,
        String privateEmail,
        String privatePhoneNumber
) {
    @Mapper
    public interface ContactDtoMapper extends DtoMapper<Contact, ContactDto> {}

    public static ContactDtoMapper mapper() {
        return Mappers.getMapper(ContactDtoMapper.class);
    }
}
