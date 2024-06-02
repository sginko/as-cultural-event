package com.example.cultural_event.account;

import com.example.cultural_event.account.dto.AccountRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(AccountRequestDto accountRequestDto) {
        return new AccountEntity(accountRequestDto.getName(), accountRequestDto.getCity(), accountRequestDto.getEmail());
    }
}
