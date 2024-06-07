package com.example.cultural_event.notification.service.notificationReaderService;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.eventService.EventService;
import com.example.cultural_event.notification.dto.NotificationResponseDto;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionRepository;
import com.example.cultural_event.subscription.service.SubscriptionService;
import com.example.cultural_event.user.dto.UserIdRequestDto;
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
class NotificationReaderServiceTest {
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
    @BeforeEach
    void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    public void should_get_list_notifications_only_with_subscription() {
        //given
        String eventName = "Test Event Name";
        String firstCity = "firstCity";
        LocalDateTime dateTimeEvent = LocalDateTime.now();
        EventRequestDto eventRequestDto = new EventRequestDto(eventName, firstCity, dateTimeEvent);
        eventService.addEvent(eventRequestDto);

        eventService.addEvent(new EventRequestDto("event", "WARSZAWA", dateTimeEvent));

        UserRequestDto userRequestDto = new UserRequestDto(CORRECT_NAME,CITY,CORRECT_EMAIL);
        userService.addNewUser(userRequestDto);
        UUID technicalUserId = userRepository.findAll().get(0).getTechnicalId();

        subscriptionService.addSubscriptionForEvent(eventRepository.findAll().get(0).getEventId(), technicalUserId);

        //when
        List<NotificationResponseDto> allNotificationsForSubscribedEvents = userService.findAllNotificationsForSubscribedEvents(technicalUserId);

        //then
        assertThat(allNotificationsForSubscribedEvents.size()).isEqualTo(1);
        assertThat(allNotificationsForSubscribedEvents.get(0).getCity()).isEqualTo(firstCity);
    }

}