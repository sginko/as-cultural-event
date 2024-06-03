package com.example.cultural_event.notification.service.notificationReaderService;

import com.example.cultural_event.notification.NotificationMapper;
import com.example.cultural_event.notification.dto.NotificationResponceDto;
import com.example.cultural_event.notification.repository.NotificationReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationReaderServiceImpl implements NotificationReaderService{
    private final NotificationReaderRepository notificationReaderRepository;
    private final NotificationMapper notificationMapper;

    public NotificationReaderServiceImpl(NotificationReaderRepository notificationReaderRepository, NotificationMapper notificationMapper) {
        this.notificationReaderRepository = notificationReaderRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationResponceDto> findAllNotifications(UUID technicalId) {
        return notificationReaderRepository
                .getAllNotificationsForAccount(technicalId)
                .stream().map(notificationEntity -> notificationMapper.fromEntity(notificationEntity))
                .toList();
    }

    @Override
    public List<NotificationResponceDto> findAllNotificationsWithSubscription(UUID technicalId) {
        return null;
    }
}
