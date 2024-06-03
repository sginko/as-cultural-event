package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;

public interface NotifyListener {
    void notifyFromEvent(EventEntity event);
//    void notifyFromSubscription(SubscriptionEntity subscription);
}