package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.notification.repository.NotificationRepository;
import com.example.cultural_event.notification.service.emailService.EmailService;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.service.SubscriptionReaderService;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.service.UserReaderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService, NotificationListener {
    private final UserReaderService userReaderService;
    private final NotificationRepository notificationRepository;
    private final SubscriptionReaderService subscriptionReaderService;
    private final EmailService emailService;

    public NotificationServiceImpl(UserReaderService userReaderService, NotificationRepository notificationRepository, SubscriptionReaderService subscriptionReaderService, EmailService emailService) {
        this.userReaderService = userReaderService;
        this.notificationRepository = notificationRepository;
        this.subscriptionReaderService = subscriptionReaderService;
        this.emailService = emailService;
    }

    @Override
    public void sendNotificationsForAllUsersAboutEvent(EventEntity event, List<UserEntity> users, String content) {
        for (UserEntity user : users) {
            user.receiveNotification(event.getEventName(), content);
        }
    }

    @Override
    public void sendNotificationsAboutUpcomingEvent(EventEntity event, List<SubscriptionEntity> subscriptions) {
        for (SubscriptionEntity subscription : subscriptions) {
            UserEntity user = subscription.getUser();
            NotificationEntity notificationEntity = new NotificationEntity(event.getEventId(), "Reminder: " + event.getEventName() + " starts in an hour in " + event.getCity(), event.getCity());
            notificationRepository.save(notificationEntity);
            user.receiveNotification(event.getEventName(), "starts in an hour");

            String email = subscription.getUser().getEmail();
            String subject = notificationEntity.getNotification();
            String content = notificationEntity.getNotification();
            emailService.sendEmail(email, subject, content);
        }
    }

    @Override
    public void sendNotificationsAboutCanceledEvent(EventEntity event, List<SubscriptionEntity> subscriptions) {
        for (SubscriptionEntity subscription : subscriptions) {
            NotificationEntity notificationEntity = new NotificationEntity(event.getEventId(), "Attention: " + event.getEventName() + " has cancelled in " + event.getCity(), event.getCity());
            notificationRepository.save(notificationEntity);
            String email = subscription.getUser().getEmail();
            String subject = notificationEntity.getNotification();
            String content = notificationEntity.getNotification();
            emailService.sendEmail(email, subject, content);
        }
    }

    @Override
    @Transactional
    public void notificationAboutCreationEvent(EventEntity event) {
        NotificationEntity notificationEntity = new NotificationEntity(event.getEventId(), "Notification about " + event.getEventName(), event.getCity());
        notificationRepository.save(notificationEntity);
        List<UserEntity> users = userReaderService.findByCity(event.getCity());
        sendNotificationsForAllUsersAboutEvent(event, users, "has been created");
    }

    @Override
    @Transactional
    public void notificationAboutDeletionEvent(EventEntity event) {
        NotificationEntity notificationEntity = new NotificationEntity(event.getEventId(), "Notification about cancelled " + event.getEventName(), event.getCity());
        notificationRepository.save(notificationEntity);
        List<UserEntity> users = userReaderService.findByCity(event.getCity());
        sendNotificationsForAllUsersAboutEvent(event, users, "has been cancelled");

        List<SubscriptionEntity> subscriptions = subscriptionReaderService.findByEvent(event);
        sendNotificationsAboutCanceledEvent(event, subscriptions);
    }
}
