package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountRequestDto;
import com.example.cultural_event.notification.NotificationEntity;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    void addNewAccount(AccountRequestDto accountRequestDto);

    List<NotificationEntity> findAllNotifications(UUID technicalId);
}
