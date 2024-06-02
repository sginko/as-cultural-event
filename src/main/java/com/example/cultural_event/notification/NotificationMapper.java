package com.example.cultural_event.notification;

import com.example.cultural_event.notification.dto.NotificationResponceDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationResponceDto fromEntity(NotificationEntity notificationEntity) {
        return new NotificationResponceDto(notificationEntity.getEvents(), notificationEntity.getAccount(), notificationEntity.getNotification());
    }
}
