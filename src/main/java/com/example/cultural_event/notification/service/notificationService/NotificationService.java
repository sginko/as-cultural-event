package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.event.model.enity.EventEntity;

import java.util.List;

public interface NotificationService {
    void sendNotifications(EventEntity event, List<AccountEntity> accounts);
}
