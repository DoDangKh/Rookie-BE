package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.AccountDto;
import com.rookie.rookiee.entity.Account;

public class AccountMapper {
    public static AccountDto accountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getUserName(),
                account.getPassword());
    }

    public static Account account(AccountDto accountDto) {
        Account temp = new Account();
        temp.setId(accountDto.getId());
        temp.setUserName(accountDto.getUserName());
        temp.setPassword(accountDto.getPassword());
        return temp;
    }
}
