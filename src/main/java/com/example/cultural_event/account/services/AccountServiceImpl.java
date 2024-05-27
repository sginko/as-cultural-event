package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountReader;
import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountRepository;
import com.example.cultural_event.account.AccountRequestDto;
import com.example.cultural_event.notification.NotificationEntity;
import com.example.cultural_event.notification.NotificationReader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountReader accountReader;
    private final NotificationReader notificationReader;

    public AccountServiceImpl(AccountRepository accountRepository, AccountReader accountReader, NotificationReader notificationReader) {
        this.accountRepository = accountRepository;
        this.accountReader = accountReader;
        this.notificationReader = notificationReader;
    }

    @Override
    public void addNewAccount(AccountRequestDto accountRequestDto) {
        accountRepository.save(new AccountEntity(accountRequestDto.getName(),
                accountRequestDto.getCity(), accountRequestDto.getEmail()));
    }

    @Override
    public List<NotificationEntity> findAllNotifications(UUID technicalId) {
        AccountEntity account = accountRepository.findByTechnicalId(technicalId);
        return notificationReader.getAllNotificationsForAccount(account);
    }
}
