package com.example.cultural_event.user.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.eventService.EventService;
import com.example.cultural_event.notification.dto.NotificationResponseDto;
import com.example.cultural_event.notification.repository.NotificationRepository;
import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {
    private final String CORRECT_NAME = "Flo69";
    private final String CORRECT_EMAIL = "Destin.Smith@yahoo.com";
    private final String CITY = "South Luthermouth";
    private final UUID TECHNICAL_ID = UUID.randomUUID();

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @AfterEach
    void tearDown() {
        notificationRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void should_create_new_user_when_data_are_correct() {
        //given
        UserRequestDto userRequestDto = prepareUserRequestDto(CORRECT_NAME, CITY, CORRECT_EMAIL);

        //when
        userService.addNewUser(userRequestDto);
        List<UserEntity> all = userRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void should_create_new_user_when_event_is_exist_in_this_city() {
        //given
        EventRequestDto event = new EventRequestDto("event", CITY, LocalDateTime.now());
        eventService.addEvent(event);
        UserRequestDto userRequestDto = prepareUserRequestDto(CORRECT_NAME, CITY, CORRECT_EMAIL);

        //when
        userService.addNewUser(userRequestDto);
        List<UserEntity> all = userRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void should_find_all_notifications_for_account_by_id() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(CORRECT_NAME, CITY, CORRECT_EMAIL);
        userService.addNewUser(userRequestDto);
        List<UserEntity> all = userRepository.findAll();
        UUID technicalId = all.get(0).getTechnicalId();
        EventRequestDto event = new EventRequestDto("event", CITY, LocalDateTime.now());
        eventService.addEvent(event);

        //when
        List<NotificationResponseDto> allNotifications = userService.findAllNotifications(technicalId);

        //then
        assertThat(allNotifications.size()).isEqualTo(1);
    }

    private UserRequestDto prepareUserRequestDto(String name, String city, String email) {
        return new UserRequestDto(name, city, email);
    }
}
