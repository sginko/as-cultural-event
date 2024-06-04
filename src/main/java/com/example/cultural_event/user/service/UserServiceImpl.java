package com.example.cultural_event.user.service;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.service.eventReaderService.EventReaderService;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.mapper.UserMapper;
import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.user.repository.UserRepository;
import com.example.cultural_event.notification.model.dto.NotificationResponceDto;
import com.example.cultural_event.notification.model.service.notificationReaderService.NotificationReaderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final NotificationReaderService notificationReaderService;
    private final UserMapper userMapper;
    private final EventReaderService eventReaderService;

    public UserServiceImpl(UserRepository userRepository, NotificationReaderService notificationReaderService, UserMapper userMapper, EventReaderService eventReaderService) {
        this.userRepository = userRepository;
        this.notificationReaderService = notificationReaderService;
        this.userMapper = userMapper;
        this.eventReaderService = eventReaderService;
    }

    @Override
    public void addNewUser(UserRequestDto userRequestDto) {
        UserEntity user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);

        //user.receiveNotification(event.getEventName(), "has been created");

        List<EventEntity> events = eventReaderService.findByCity(user.getCity());
        //createNotifications(event, users);
        sendNotificationsForUsers(events, user);
    }

    private void sendNotificationsForUsers(List<EventEntity> events, UserEntity user) {
        for (EventEntity event : events) {
            user.receiveNotification(event.getEventName(), "has been created");
        }
    }

    @Override
    public List<NotificationResponceDto> findAllNotifications(UUID technicalId) {
        List<NotificationResponceDto> allNotifications = notificationReaderService.findAllNotifications(technicalId);
        return allNotifications;
    }

    @Override
    public List<NotificationResponceDto> findAllNotificationsForSubscribedEvents(UUID technicalId) {
        List<NotificationResponceDto> allNotificationsWithSubscribtion = notificationReaderService.findAllNotificationsWithSubscription(technicalId);
        return allNotificationsWithSubscribtion;
    }
}