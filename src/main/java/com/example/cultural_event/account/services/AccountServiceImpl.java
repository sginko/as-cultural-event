package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountReader;
import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountRepository;
import com.example.cultural_event.account.AccountRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountReader accountReader;

    public AccountServiceImpl(AccountRepository accountRepository, AccountReader accountReader) {
        this.accountRepository = accountRepository;
        this.accountReader = accountReader;
    }

    @Override
    public void addNewAccount(AccountRequestDto accountRequestDto) {
        accountRepository.save(new AccountEntity(accountRequestDto.getName(),
                accountRequestDto.getCity(), accountRequestDto.getEmail()));
    }
}
