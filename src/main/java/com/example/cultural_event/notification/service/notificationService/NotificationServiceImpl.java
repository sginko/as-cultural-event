package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.account.service.AccountReaderService;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.notification.repository.NotificationRepository;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService, NotifyListener {
    private final AccountReaderService accountReaderService;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(AccountReaderService accountReaderService, NotificationRepository notificationRepository) {
        this.accountReaderService = accountReaderService;
        this.notificationRepository = notificationRepository;
    }

    public void createNotifications(EventEntity event, List<AccountEntity> accounts) {
        sendNotifications(event, accounts);
    }

    @Override
    @Transactional
    public void sendNotifications(EventEntity event, List<AccountEntity> accounts) {
        for (AccountEntity account : accounts) {
            //NotificationEntity notificationEntity = new NotificationEntity(event, account, "Notification about " + event.getEventName() + " for your city " + event.getCity());
            NotificationEntity notificationEntity = new NotificationEntity("Notification about " + event.getEventName(),event.getCity());
            notificationRepository.save(notificationEntity);
            account.receiveNotification(event.getEventName(), "has been created");
        }
    }

    @Override
    @Transactional
    public void sendNotificationsForSubscription(EventEntity event, List<SubscriptionEntity> subscriptions) {
        for (SubscriptionEntity subscription : subscriptions) {
            AccountEntity account = subscription.getAccount();
            //NotificationEntity notificationEntity = new NotificationEntity(event, account, "Reminder: " + event.getEventName() + " starts in an hour in " + event.getCity());

            //notificationRepository.save(notificationEntity);
            account.receiveNotification(event.getEventName(), "starts in an hour");
        }
    }

    @Override
    public void notifyFromEvent(EventEntity event) {
        List<AccountEntity> accounts = accountReaderService.findByCity(event.getCity());
        createNotifications(event, accounts);
    }
}