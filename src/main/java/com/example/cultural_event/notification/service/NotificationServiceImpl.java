package com.example.cultural_event.notification.service;

import com.example.cultural_event.account.AccountReader;
import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.notification.NotificationEntity;
import com.example.cultural_event.notification.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService, NotifyListener {
    private final NotificationRepository notificationRepository;
    private final AccountReader accountReader;

    public NotificationServiceImpl(NotificationRepository notificationRepository, AccountReader accountReader) {
        this.notificationRepository = notificationRepository;
        this.accountReader = accountReader;
    }

    @Transactional
    public void createNotifications(EventEntity event, List<AccountEntity> accounts) {
        for (AccountEntity account : accounts) {
            notificationRepository.save(new NotificationEntity(account, event));
        }
        sendNotifications(event, accounts);
    }

    public void sendNotifications(EventEntity event, List<AccountEntity> accounts) {
        for (AccountEntity account : accounts) {
            account.receiveNotification(event.getEventName());
        }
    }

    @Override
    public void notifyFromEvent(EventEntity event) {
        List<AccountEntity> accounts = accountReader.findByCity(event.getCity());
        createNotifications(event, accounts);
    }
}
