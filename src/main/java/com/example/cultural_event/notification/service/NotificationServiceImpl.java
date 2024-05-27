package com.example.cultural_event.notification.service;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountReader;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.notification.NotificationEntity;
import com.example.cultural_event.notification.NotificationReader;
import com.example.cultural_event.notification.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService, NotifyListener {

    private final AccountReader accountReader;
    private final NotificationRepository notificationRepository;


    public NotificationServiceImpl(AccountReader accountReader, NotificationRepository notificationRepository) {
        this.accountReader = accountReader;
        this.notificationRepository = notificationRepository;
    }

    public void createNotifications(EventEntity event, List<AccountEntity> accounts) {
        sendNotifications(event, accounts);
    }

    @Override
    @Transactional
    public void sendNotifications(EventEntity event, List<AccountEntity> accounts) {
        for (AccountEntity account : accounts) {
            NotificationEntity notificationEntity = new NotificationEntity(event, account, "Notification about " + event.getEventName() + " for your city " + event.getCity());
            notificationRepository.save(notificationEntity);
            account.receiveNotification(event.getEventName());
        }
    }

    @Override
    public void notifyFromEvent(EventEntity event) {
        List<AccountEntity> accounts = accountReader.findByCity(event.getCity());
        createNotifications(event, accounts);
    }
}
