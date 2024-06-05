package com.example.cultural_event.notification.service;

import com.example.cultural_event.notification.model.service.notificationService.NotificationListener;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.repository.UserRepository;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.notification.model.enity.NotificationEntity;
import com.example.cultural_event.notification.model.repository.NotificationRepository;
import com.example.cultural_event.notification.model.service.notificationService.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
    private UserRepository userRepository;
    @Autowired
    private NotificationServiceImpl notificationService;
    @Autowired
    private NotificationListener notificationListener;

    @BeforeEach
    void tearDown() {
        notificationRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void should_create_notification_for_event_successfully() {
        //given
        EventEntity event = prepareEvent(NAME_EVENT, CITY, DATE_OF_EVENT);
        eventRepository.save(event);
        //when
        notificationListener.notificationFromEvent(event);
        //and
        List<NotificationEntity> all = notificationRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getNotification().contains(CITY));
    }

    @Test
    public void should_create_notification_for_event_successfully_when_user_is_exist() {
        //given
        EventEntity event = prepareEvent(NAME_EVENT, CITY, DATE_OF_EVENT);
        eventRepository.save(event);
        UserEntity userEntity = prepareUser(NAME_ACCOUNT, CITY, EMAIL);
        userRepository.save(userEntity);
        //when
        notificationListener.notificationFromEvent(event);
        //and
        List<NotificationEntity> all = notificationRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getNotification().contains(CITY));
    }

    private UserEntity prepareUser(String nameAccount, String city, String email) {
        return new UserEntity(nameAccount, city, email);
    }

    private EventEntity prepareEvent(String nameEvent, String city, LocalDateTime dateOfEvent) {
        return new EventEntity(nameEvent, city, dateOfEvent);
    }
}