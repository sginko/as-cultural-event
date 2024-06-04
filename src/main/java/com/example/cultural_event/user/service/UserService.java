package com.example.cultural_event.user.service;

import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.notification.model.dto.NotificationResponceDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void addNewUser(UserRequestDto userRequestDto);

    List<NotificationResponceDto> findAllNotifications(UUID technicalId);

    List<NotificationResponceDto> findAllNotificationsForSubscribedEvents(UUID technicalId);
}
