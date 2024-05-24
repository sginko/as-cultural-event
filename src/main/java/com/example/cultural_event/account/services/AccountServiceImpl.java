package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountRepository;
import com.example.cultural_event.account.AccountRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addNewAccount(AccountRequestDto accountRequestDto) {
        accountRepository.save(new AccountEntity(accountRequestDto.getName(),
                accountRequestDto.getCity(), accountRequestDto.getEmail()));
    }
}
