package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.user.entity.UserEntity;

import java.util.List;

public interface NotificationListener {

    void notificationAboutCreationEvent(EventEntity event);

    void notificationAboutDeletionEvent(EventEntity event, List<UserEntity> users);
}
