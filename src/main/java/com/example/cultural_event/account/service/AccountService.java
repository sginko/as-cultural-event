package com.example.cultural_event.account.service;

import com.example.cultural_event.account.dto.AccountRequestDto;
import com.example.cultural_event.notification.dto.NotificationResponceDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    void addNewAccount(AccountRequestDto accountRequestDto);

    List<NotificationResponceDto> findAllNotifications(UUID technicalId);
}
