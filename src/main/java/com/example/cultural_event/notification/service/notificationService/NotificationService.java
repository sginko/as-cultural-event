package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;

import java.util.List;

public interface NotificationService {
    void sendNotifications(EventEntity event, List<AccountEntity> accounts);

    void sendNotificationsForSubscription(EventEntity event, List<SubscriptionEntity> subscriptions);
}
