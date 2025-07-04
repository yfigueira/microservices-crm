package org.example.accountservice.account.domain;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account create(Account account);

    List<Account> getAll();

    Account getById(UUID id);
}
