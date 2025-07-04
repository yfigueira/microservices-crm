package org.example.accountservice.account.persistence;

import org.example.accountservice.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccountMapper {

    Account toDomain(AccountEntity entity);

    AccountEntity toEntity(Account domain);
}
