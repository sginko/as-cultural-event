package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountRequestDto;
import org.springframework.stereotype.Service;


public interface AccountService {
    public void addNewAccount(AccountRequestDto accountRequestDto);
}
