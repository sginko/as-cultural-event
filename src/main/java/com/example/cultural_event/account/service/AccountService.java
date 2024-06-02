package com.example.cultural_event.account.service;

import com.example.cultural_event.account.dto.AccountRequestDto;
import com.example.cultural_event.notification.enity.NotificationEntity;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    void addNewAccount(AccountRequestDto accountRequestDto);

    List<NotificationEntity> findAllNotifications(UUID technicalId);
}
