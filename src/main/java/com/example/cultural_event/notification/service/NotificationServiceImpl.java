package com.example.cultural_event.notification.service;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountReader;
import com.example.cultural_event.event.model.enity.EventEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService, NotifyListener {

    private final AccountReader accountReader;

    public NotificationServiceImpl(AccountReader accountReader) {
        this.accountReader = accountReader;
    }

    public void createNotifications(EventEntity event, List<AccountEntity> accounts) {
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
