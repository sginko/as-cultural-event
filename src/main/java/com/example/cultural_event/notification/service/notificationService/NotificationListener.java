package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;

public interface NotificationListener {

    void notificationAboutCreationEvent(EventEntity event);

    void notificationAboutDeletionEvent(EventEntity event);

    void notificationAboutUpdateEvent(EventEntity event);
}
