package com.example.cultural_event.notification.model.service.notificationService;

import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.event.model.enity.EventEntity;
//import com.example.cultural_event.subscription.entity.SubscriptionEntity;

import java.util.List;

public interface NotificationService {
    void sendNotifications(EventEntity event, List<UserEntity> accounts);

//    void sendNotificationsForSubscription(EventEntity event, List<SubscriptionEntity> subscriptions);
}
