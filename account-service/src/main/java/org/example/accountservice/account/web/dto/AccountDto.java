package org.example.accountservice.account.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.example.accountservice.account.domain.Account;
import org.example.accountservice.common.web.DtoMapper;
import org.example.accountservice.contact.web.dto.ContactSummary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Builder
public record AccountDto(
        UUID id,
        @NotBlank(message = "Name required")
        String name,
        @NotBlank(message = "Country required")
        String country,
        @NotBlank(message = "City required")
        String city,
        String street,
        String streetNumber,
        String zipCode,
        @NotBlank(message = "Tax identification number required")
        String tin,
        String webUrl,
        List<ContactSummary> contacts
) {
    @Mapper
    public interface AccountDtoMapper extends DtoMapper<Account, AccountDto> {}

    public static AccountDtoMapper mapper() {
        return Mappers.getMapper(AccountDtoMapper.class);
    }
}
