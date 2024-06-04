package com.example.cultural_event.notification.model.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.user.entity.UserEntity;

import java.util.List;

public interface NotificationService {

    void sendNotifications(EventEntity event, List<UserEntity> accounts);
}
