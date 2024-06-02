package com.example.cultural_event.notification.service;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.account.repository.AccountRepository;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.notification.repository.NotificationRepository;
import com.example.cultural_event.notification.service.notificationService.NotificationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationServiceImplTest {
    private final String NAME_EVENT = "HOLIDAY";
    private final String CITY = "BERLIN";
    private final LocalDateTime DATE_OF_EVENT = LocalDateTime.now().plusDays(10);
    private final String NAME_ACCOUNT = "LoginFirst";
    private final String EMAIL = "aaa@gmail.com";
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private NotificationServiceImpl notificationService;
    @AfterEach
    void tearDown() {
        notificationRepository.deleteAll();
    }

    @Test
    public void should_create_notification_for_event_successfully() {
        //given
        EventEntity event = prepareEvent(NAME_EVENT, CITY, DATE_OF_EVENT);
        AccountEntity accountEntity = prapareAccount(NAME_ACCOUNT, CITY, EMAIL);
        List<AccountEntity> accountEntityList = List.of(accountEntity);
        eventRepository.save(event);
        accountRepository.save(accountEntity);
        //when
        notificationService.sendNotifications(event,accountEntityList);
        //and
        List<NotificationEntity> all = notificationRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getNotification().contains(CITY));
    }

    private AccountEntity prapareAccount(String nameAccount, String city, String email) {
        return new AccountEntity(nameAccount, city, email);
    }

    private EventEntity prepareEvent(String nameEvent, String city, LocalDateTime dateOfEvent) {
        return new EventEntity(nameEvent, city, dateOfEvent);
    }
}