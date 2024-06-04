package com.example.cultural_event.notification.model.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.notification.model.enity.NotificationEntity;
import com.example.cultural_event.notification.model.repository.NotificationRepository;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.service.UserReaderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService, NotificationListener {
    private final UserReaderService userReaderService;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(UserReaderService userReaderService, NotificationRepository notificationRepository) {
        this.userReaderService = userReaderService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public void sendNotifications(EventEntity event, List<UserEntity> users) {
        for (UserEntity user : users) {
            user.receiveNotification(event.getEventName(), "has been created");
        }
    }

    @Override
    @Transactional
    public void notificationFromEvent(EventEntity event) {
        NotificationEntity notificationEntity = new NotificationEntity("Notification about " + event.getEventName(), event.getCity());
        notificationRepository.save(notificationEntity);

        List<UserEntity> users = userReaderService.findByCity(event.getCity().toUpperCase());
        sendNotifications(event, users);
    }
}
