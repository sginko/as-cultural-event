package com.example.cultural_event.account.service;

import com.example.cultural_event.account.AccountMapper;
import com.example.cultural_event.account.dto.AccountRequestDto;
import com.example.cultural_event.account.repository.AccountRepository;
import com.example.cultural_event.notification.dto.NotificationResponceDto;
import com.example.cultural_event.notification.service.notificationReaderService.NotificationReaderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final NotificationReaderService notificationReaderService;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, NotificationReaderService notificationReaderService,
                              AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.notificationReaderService = notificationReaderService;
        this.accountMapper = accountMapper;
    }

    @Override
    public void addNewAccount(AccountRequestDto accountRequestDto) {
        accountRepository.save(accountMapper.toEntity(accountRequestDto));
    }

    @Override
    public List<NotificationResponceDto> findAllNotifications(UUID technicalId) {
        List<NotificationResponceDto> allNotifications = notificationReaderService.findAllNotifications(technicalId);
        return allNotifications;
    }

    @Override
    public List<NotificationResponceDto> findAllNotificationsForSubscribedEvents(UUID technicalId) {
        List<NotificationResponceDto> allNotificationsWithSubscribtion = notificationReaderService.findAllNotificationsWithSubscription(technicalId);
        return allNotificationsWithSubscribtion;
    }
}