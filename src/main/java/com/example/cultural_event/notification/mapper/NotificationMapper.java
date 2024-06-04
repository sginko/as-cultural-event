package com.example.cultural_event.notification.mapper;

import com.example.cultural_event.notification.model.dto.NotificationResponseDto;
import com.example.cultural_event.notification.model.enity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationResponseDto fromEntity(NotificationEntity notificationEntity) {
        return new NotificationResponseDto(notificationEntity.getNotification(), notificationEntity.getCity());
    }
}
