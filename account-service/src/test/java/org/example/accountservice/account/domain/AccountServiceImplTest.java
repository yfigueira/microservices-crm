package org.example.accountservice.account.domain;

import org.example.accountservice.exception.AccountServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountRepository repository;

    @InjectMocks
    AccountServiceImpl SUT;

    @Test
    void whenAlreadyExists_ShouldThrowAccountServiceExceptionAlreadyExists() {
        // given
        var newAccount = Account.builder()
                .tin("0000000000")
                .build();

        Mockito.when(repository.existsByTin(newAccount.tin())).thenReturn(true);

        // when, then
        assertThatThrownBy(() -> SUT.create(newAccount))
                .isInstanceOf(AccountServiceException.ResourceAlreadyExistsException.class)
                .hasMessage("Account already exists :: %s".formatted(newAccount.tin()));
    }

    @Test
    void whenNotAlreadyExists_ShouldReturnCreatedAccount() {
        // given
        var newAccount = Account.builder()
                .tin("0000000000")
                .build();

        var createdAccount = Account.builder()
                .id(UUID.randomUUID())
                .tin("0000000000")
                .build();

        Mockito.when(repository.create(newAccount)).thenReturn(createdAccount);

        // when
        var result = SUT.create(newAccount);

        // then
        assertThat(result, is(equalTo(createdAccount)));
    }
}