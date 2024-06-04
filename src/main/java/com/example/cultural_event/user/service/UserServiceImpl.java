package com.example.cultural_event.user.service;

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

    public UserServiceImpl(UserRepository userRepository, NotificationReaderService notificationReaderService,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.notificationReaderService = notificationReaderService;
        this.userMapper = userMapper;
    }

    @Override
    public void addNewUser(UserRequestDto userRequestDto) {
        userRepository.save(userMapper.toEntity(userRequestDto));
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