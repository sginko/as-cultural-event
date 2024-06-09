package com.example.cultural_event.subscription.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.eventService.EventService;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionRepository;
import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.user.repository.UserRepository;
import com.example.cultural_event.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
class SubscriptionServiceTest {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    void should_subscribe_for_event() {
        //given
        String userName = "TestName";
        String email = "test@mail.com";
        String userCity = "City";
        UserRequestDto user = new UserRequestDto(userName, userCity, email);
        userService.addNewUser(user);
        UUID technicalUserId = userRepository.findAll().get(0).getTechnicalId();

        String eventName = "Test Event Name";
        String eventCity = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();
        EventRequestDto event = new EventRequestDto(eventName, eventCity, dateTimeEvent);
        eventService.addEvent(event);
        UUID technicalEventId = eventRepository.findAll().get(0).getEventId();

        //when
        subscriptionService.addSubscriptionForEvent(technicalEventId, technicalUserId);

        //then
        assertThat(subscriptionRepository.findAll().size()).isEqualTo(1);
    }

//    @Test
//    void should_get_list_subscription_without_unsubscribed_events() {
//        //given
//        String userName = "TestName";
//        String email = "test@mail.com";
//        String userCity = "City";
//        UserRequestDto user = new UserRequestDto(userName, userCity, email);
//        userService.addNewUser(user);
//        UUID technicalUserId = userRepository.findAll().get(0).getTechnicalId();
//
//        String eventName = "Test Event Name";
//        String eventCity = "City";
//        LocalDateTime dateTimeEvent = LocalDateTime.now();
//        EventRequestDto event = new EventRequestDto(eventName, eventCity, dateTimeEvent);
//        eventService.addEvent(event);
//        UUID technicalEventId = eventRepository.findAll().get(0).getEventId();
//
//        subscriptionService.addSubscriptionForEvent(technicalEventId, technicalUserId);
//
//        String secondEventName = "Second Test Event Name";
//        String secondEventCity = "City";
//        LocalDateTime secondDateTimeEvent = LocalDateTime.now();
//        EventRequestDto secondEvent = new EventRequestDto(secondEventName, secondEventCity, secondDateTimeEvent);
//        eventService.addEvent(secondEvent);
//        UUID secondTechnicalEventId = eventRepository.findAll().get(1).getEventId();
//
//        subscriptionService.addSubscriptionForEvent(secondTechnicalEventId, technicalUserId);
//
//        //when
//        subscriptionService.deleteSubscriptionForEvent(technicalEventId, technicalUserId);
//        List<SubscriptionEntity> all = subscriptionRepository.findAll();
//        //then
//        assertThat(all.size()).isEqualTo(1);
//        assertThat(subscriptionRepository.findAll().get(0).getEvents().getEventName()).isEqualTo(secondEventName);
//    }
}