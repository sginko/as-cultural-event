package com.example.cultural_event.notification.model.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;

public interface NotificationListener {

    void notificationFromEvent(EventEntity event);

    //    void notifyFromSubscription(SubscriptionEntity subscription);

}
