package org.example.accountservice.account.domain;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.exception.AccountServiceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Account create(Account account) {
        if (repository.existsByTin(account.tin())) {
            throw AccountServiceException.alreadyExists(Account.class, account.tin());
        }
        return repository.create(account);
    }
}
