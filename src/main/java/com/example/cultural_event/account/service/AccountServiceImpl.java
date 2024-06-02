package com.example.cultural_event.account.service;

import com.example.cultural_event.account.repository.AccountReader;
import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.account.repository.AccountRepository;
import com.example.cultural_event.account.dto.AccountRequestDto;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.notification.repository.NotificationReader;
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
