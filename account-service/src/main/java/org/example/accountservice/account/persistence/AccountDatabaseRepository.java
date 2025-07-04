package org.example.accountservice.account.persistence;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.account.domain.Account;
import org.example.accountservice.account.domain.AccountRepository;
import org.example.accountservice.exception.AccountServiceException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<Account> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Account update(UUID id, Account account) {
        return jpaRepository.findById(id)
                .map(entity -> mapper.updateEntity(account, entity))
                .map(jpaRepository::save)
                .map(mapper::toDomain)
                .orElseThrow(() -> AccountServiceException.notFound(Account.class, id));
    }

    @Override
    public void delete(UUID id) {
        var entity = jpaRepository.findById(id)
                .orElseThrow(() -> AccountServiceException.notFound(Account.class, id));
        jpaRepository.delete(entity);
    }

    @Override
    public Boolean existsByTin(String tin) {
        return jpaRepository.existsByTin(tin);
    }
}
