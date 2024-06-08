package com.example.cultural_event.notification.mapper;

import com.example.cultural_event.notification.dto.NotificationResponseDto;
import com.example.cultural_event.notification.enity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationResponseDto fromEntity(NotificationEntity notificationEntity) {
        return new NotificationResponseDto(notificationEntity.getEventTechnicalId(), notificationEntity.getNotification(), notificationEntity.getCity());
    }
}
