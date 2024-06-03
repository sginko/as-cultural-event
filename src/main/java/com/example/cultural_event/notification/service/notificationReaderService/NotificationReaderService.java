package com.example.cultural_event.notification.service.notificationReaderService;

import com.example.cultural_event.notification.dto.NotificationResponceDto;

import java.util.List;
import java.util.UUID;

public interface NotificationReaderService {
    List<NotificationResponceDto> findAllNotifications(UUID technicalId);
    List<NotificationResponceDto> findAllNotificationsWithSubscription(UUID technicalId);
}
