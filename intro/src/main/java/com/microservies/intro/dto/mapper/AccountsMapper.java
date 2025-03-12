package com.microservies.intro.dto.mapper;

import com.microservies.intro.dto.AccountsDto;
import com.microservies.intro.entity.AccountsEntity;

public final class AccountsMapper {
    private AccountsMapper(){}

    public static AccountsDto mapToAccountsDto(AccountsEntity accountsEntity) {
        return new AccountsDto(accountsEntity.getAccountNumber()
                , accountsEntity.getCustomerId()
                , accountsEntity.getAccountType()
                , accountsEntity.getBranchAddress());
    }

    public static AccountsEntity mapToAccountsEntity(AccountsDto accountsDto){
        return new AccountsEntity(accountsDto.accountNumber()
                , accountsDto.customerId()
                , accountsDto.accountType()
                , accountsDto.branchAddress());
    }
}
