package org.example.accountservice.account.persistence;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.account.domain.Account;
import org.example.accountservice.account.domain.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class AccountDatabaseRepository implements AccountRepository {

    private final AccountJpaRepository jpaRepository;
    private final AccountMapper mapper;

    @Override
    public Account create(Account account) {
        var entity = mapper.toEntity(account);
        var created = jpaRepository.save(entity);
        return mapper.toDomain(created);
    }

    @Override
    public Boolean existsByTin(String tin) {
        return jpaRepository.existsByTin(tin);
    }
}
