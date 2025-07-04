package org.example.accountservice.account.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accountservice.account.domain.AccountService;
import org.example.accountservice.account.web.dto.AccountDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
