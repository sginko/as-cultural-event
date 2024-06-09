package com.example.cultural_event.subscription.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.eventService.EventService;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionRepository;
import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.user.repository.UserRepository;
import com.example.cultural_event.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SubscriptionReaderServiceTest {
    private final String CORRECT_NAME = "Flo69";
    private final String CORRECT_EMAIL = "Destin.Smith@yahoo.com";
    private final String CITY = "South Luthermouth";

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionReaderService subscriptionReaderService;

    @BeforeEach
    void tearDown() {
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }

//    @Test
//    public void should_find_subscription_by_event_successfully() {
//        //given
//        String eventName = "Test Event Name";
//        String firstCity = "firstCity";
//        LocalDateTime dateTimeEvent = LocalDateTime.now();
//        EventRequestDto eventRequestDto = new EventRequestDto(eventName, firstCity, dateTimeEvent);
//        eventService.addEvent(eventRequestDto);
//
//        eventService.addEvent(new EventRequestDto("event", "WARSZAWA", dateTimeEvent));
//        EventEntity currentEvent = eventRepository.findAll().get(0);
//
//        UserRequestDto userFirstRequestDto = new UserRequestDto(CORRECT_NAME, CITY, CORRECT_EMAIL);
//        userService.addNewUser(userFirstRequestDto);
//        UUID technicalFirstUserId = userRepository.findAll().get(0).getTechnicalId();
//
//        UserRequestDto userSecondRequestDto = new UserRequestDto(CORRECT_NAME, "WARSZAWA", CORRECT_EMAIL);
//        userService.addNewUser(userSecondRequestDto);
//        UUID technicalSecondUserId = userRepository.findAll().get(1).getTechnicalId();
//
//        subscriptionService.addSubscriptionForEvent(currentEvent.getEventId(), technicalFirstUserId);
//        subscriptionService.addSubscriptionForEvent(currentEvent.getEventId(), technicalSecondUserId);
//
//        //when
//        List<SubscriptionEntity> subscriptions = subscriptionReaderService.findByEvent(currentEvent);
//
//        //then
//        assertThat(subscriptions.size()).isEqualTo(2);
//        assertThat(subscriptions.get(0).getUser().getCity()).isEqualTo(CITY);
//        assertThat(subscriptions.get(1).getUser().getCity()).isEqualTo("WARSZAWA");
//    }
}