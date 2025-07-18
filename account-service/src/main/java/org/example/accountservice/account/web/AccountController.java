package org.example.accountservice.account.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accountservice.account.domain.AccountService;
import org.example.accountservice.account.web.dto.AccountDto;
import org.example.accountservice.account.web.dto.AccountSummary;
import org.example.accountservice.contact.web.dto.ContactDto;
import org.example.accountservice.contact.web.dto.ContactSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AccountController {

    private final AccountService service;

    @PostMapping
    public AccountDto create(@RequestBody @Valid AccountDto dto) {
        var account = AccountDto.mapper().toDomain(dto);
        var created = service.create(account);
        return AccountDto.mapper().toDto(created);
    }

    @GetMapping
    public List<AccountSummary> getAll() {
        return service.getAll().stream()
                .map(AccountSummary.mapper()::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public AccountDto getById(@PathVariable UUID id) {
        var account = service.getById(id);
        return AccountDto.mapper().toDto(account);
    }

    @PutMapping("{id}")
    public AccountDto update(
            @PathVariable UUID id,
            @RequestBody @Valid AccountDto dto
    ) {
        var account = AccountDto.mapper().toDomain(dto);
        var updated = service.update(id, account);
        return AccountDto.mapper().toDto(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/contacts")
    public ContactSummary addContact(
            @PathVariable("id") UUID accountId,
            @RequestBody @Valid ContactDto contactDto
    ) {
        var contact = ContactDto.mapper().toDomain(contactDto);
        var addedContact = service.addContact(accountId, contact);
        return ContactSummary.mapper().toDto(addedContact);
    }
}
