package com.example.cultural_event.notification.mapper;

import com.example.cultural_event.notification.model.dto.NotificationResponceDto;
import com.example.cultural_event.notification.model.enity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationResponceDto fromEntity(NotificationEntity notificationEntity) {
        //return new NotificationResponceDto(notificationEntity.getEvents(), notificationEntity.getAccount(), notificationEntity.getNotification());
        return new NotificationResponceDto(notificationEntity.getNotification(),notificationEntity.getCity());
    }
}
