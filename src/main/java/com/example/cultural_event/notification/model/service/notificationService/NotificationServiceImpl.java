package com.example.cultural_event.notification.model.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.notification.model.enity.NotificationEntity;
import com.example.cultural_event.notification.model.repository.NotificationRepository;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
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
    public void sendNotifications(EventEntity event, List<UserEntity> users) {
        for (UserEntity user : users) {
            user.receiveNotification(event.getEventName(), "has been created");
        }
    }

    @Override
    public void sendNotificationsForSubscription(EventEntity event, List<SubscriptionEntity> subscriptions) {
        for (SubscriptionEntity subscription : subscriptions) {
            UserEntity user = subscription.getUser();
            NotificationEntity notificationEntity = new NotificationEntity(event.getEventId(), "Reminder: " + event.getEventName() + " starts in an hour in " + event.getCity(), event.getCity());
            notificationRepository.save(notificationEntity);
            user.receiveNotification(event.getEventName(), "starts in an hour");
        }
    }

    @Override
    @Transactional
    public void notificationFromEvent(EventEntity event) {
        NotificationEntity notificationEntity = new NotificationEntity(event.getEventId(), "Notification about " + event.getEventName(), event.getCity());
        notificationRepository.save(notificationEntity);

        List<UserEntity> users = userReaderService.findByCity(event.getCity());
        sendNotifications(event, users);
    }
}
