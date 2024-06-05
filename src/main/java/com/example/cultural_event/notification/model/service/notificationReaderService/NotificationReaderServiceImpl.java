package com.example.cultural_event.notification.model.service.notificationReaderService;

import com.example.cultural_event.notification.model.mapper.NotificationMapper;
import com.example.cultural_event.notification.model.dto.NotificationResponseDto;
import com.example.cultural_event.notification.model.repository.NotificationReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationReaderServiceImpl implements NotificationReaderService {
    private final NotificationReaderRepository notificationReaderRepository;
    private final NotificationMapper notificationMapper;

    public NotificationReaderServiceImpl(NotificationReaderRepository notificationReaderRepository, NotificationMapper notificationMapper) {
        this.notificationReaderRepository = notificationReaderRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationResponseDto> findAllNotifications(UUID technicalId) {
        return notificationReaderRepository
                .getAllNotificationsForAccount(technicalId)
                .stream().map(notificationEntity -> notificationMapper.fromEntity(notificationEntity))
                .toList();
    }

    @Override
    public List<NotificationResponseDto> findAllNotificationsWithSubscription(UUID technicalId) {
        return notificationReaderRepository
                .getAllNotificationsForAccountWithSubscription(technicalId)
                .stream().map(notificationEntity -> notificationMapper.fromEntity(notificationEntity))
                .toList();
    }
}
