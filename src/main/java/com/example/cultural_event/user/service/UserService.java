package com.example.cultural_event.user.service;

import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.notification.model.dto.NotificationResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void addNewUser(UserRequestDto userRequestDto);

    List<NotificationResponseDto> findAllNotifications(UUID technicalId);

    List<NotificationResponseDto> findAllNotificationsForSubscribedEvents(UUID technicalId);
}
