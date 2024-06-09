package com.example.cultural_event.notification.service;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.notification.repository.NotificationRepository;
import com.example.cultural_event.notification.service.notificationService.NotificationListener;
import com.example.cultural_event.notification.service.notificationService.NotificationServiceImpl;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationServiceTest {
    private final Integer NUMBER_MINUTES_SAVING_NOTIFICATION = 60;
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
        notificationListener.notificationAboutCreationEvent(event);
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
        notificationListener.notificationAboutCreationEvent(event);
        //and
        List<NotificationEntity> all = notificationRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getNotification().contains(CITY));
    }

    @Test
    void should_find_all_notification_older_then_X_minutes() {
        //given
        EventEntity event = prepareEvent(NAME_EVENT, CITY, DATE_OF_EVENT);
        eventRepository.save(event);

        UUID eventId = event.getEventId();

        NotificationEntity notificationBefore = new NotificationEntity(eventId, "notification before 2 hour", CITY);
        notificationBefore.setTimeCreatingNotification(LocalDateTime.now().minusHours(2));
        notificationRepository.save(notificationBefore);

        NotificationEntity notificationNow = new NotificationEntity(eventId, "notification just now", CITY);
        notificationRepository.save(notificationNow);

        //when
        List<NotificationEntity> expiredNotifications = notificationService.getAllExpiredNotifications(NUMBER_MINUTES_SAVING_NOTIFICATION);

        //then
        assertThat(expiredNotifications.size()).isEqualTo(1);
    }

    private UserEntity prepareUser(String nameAccount, String city, String email) {
        return new UserEntity(nameAccount, city, email);
    }

    private EventEntity prepareEvent(String nameEvent, String city, LocalDateTime dateOfEvent) {
        return new EventEntity(nameEvent, city, dateOfEvent);
    }

}
