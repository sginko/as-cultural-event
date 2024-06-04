package com.example.cultural_event.notification.model.service.notificationService;

import com.example.cultural_event.notification.model.enity.NotificationEntity;
import com.example.cultural_event.notification.model.repository.NotificationRepository;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.service.UserReaderService;
import com.example.cultural_event.event.model.enity.EventEntity;
//import com.example.cultural_event.subscription.entity.SubscriptionEntity;
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

    public void createNotifications(EventEntity event, List<UserEntity> users) {
        sendNotifications(event, users);
    }

    @Override
    @Transactional
    public void sendNotifications(EventEntity event, List<UserEntity> users) {
        for (UserEntity user : users) {
            //NotificationEntity notificationEntity = new NotificationEntity(event, account, "Notification about " + event.getEventName() + " for your city " + event.getCity());
            NotificationEntity notificationEntity = new NotificationEntity("Notification about " + event.getEventName(),event.getCity());
            notificationRepository.save(notificationEntity);
            user.receiveNotification(event.getEventName(), "has been created");
        }
    }

//    @Override
//    @Transactional
//    public void sendNotificationsForSubscription(EventEntity event, List<SubscriptionEntity> subscriptions) {
//        for (SubscriptionEntity subscription : subscriptions) {
//            AccountEntity account = subscription.getAccount();
//            //NotificationEntity notificationEntity = new NotificationEntity(event, account, "Reminder: " + event.getEventName() + " starts in an hour in " + event.getCity());
//
//            //notificationRepository.save(notificationEntity);
//            account.receiveNotification(event.getEventName(), "starts in an hour");
//        }
//    }

    @Override
    public void notificationFromEvent(EventEntity event) {
        List<UserEntity> users = userReaderService.findByCity(event.getCity());
        createNotifications(event, users);
    }
}
