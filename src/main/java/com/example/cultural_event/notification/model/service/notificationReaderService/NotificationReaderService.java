package com.example.cultural_event.notification.model.service.notificationReaderService;

import com.example.cultural_event.notification.model.dto.NotificationResponseDto;

import java.util.List;
import java.util.UUID;

public interface NotificationReaderService {

    List<NotificationResponseDto> findAllNotifications(UUID technicalId);
}
