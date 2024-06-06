package com.example.cultural_event.notification.service.notificationReaderService;

import com.example.cultural_event.notification.dto.NotificationResponseDto;

import java.util.List;
import java.util.UUID;

public interface NotificationReaderService {

    List<NotificationResponseDto> findAllNotifications(UUID technicalId);

    List<NotificationResponseDto> findAllNotificationsWithSubscription(UUID technicalId);
}
