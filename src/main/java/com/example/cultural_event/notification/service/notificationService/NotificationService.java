package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.user.entity.UserEntity;

import java.util.List;

public interface NotificationService {

    void sendNotificationsForAllUsersAboutEvent(EventEntity event, List<UserEntity> users, String content);

    void sendNotificationsAboutUpcomingEvent(EventEntity event, List<SubscriptionEntity> subscriptions);

    void sendNotificationsAboutCancelledEvent(EventEntity event, List<SubscriptionEntity> subscriptions);

    void sendNotificationsAboutUpdatedEvent(EventEntity event, List<SubscriptionEntity> subscriptions);
}
