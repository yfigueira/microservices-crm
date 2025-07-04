package org.example.accountservice.account.domain;

public interface AccountRepository {

    Account create(Account account);

    Boolean existsByTin(String tin);
}
