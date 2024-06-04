package com.example.cultural_event.user.service;

import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.repository.UserRepository;
import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.eventService.EventService;
import com.example.cultural_event.notification.model.repository.NotificationRepository;
import com.example.cultural_event.notification.model.dto.NotificationResponceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
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
    public void should_create_new_account_when_data_are_correct() {
        //given
        UserRequestDto userRequestDto = prepareAccountRequestDto(TECHNICAL_ID, CORRECT_NAME, CITY, CORRECT_EMAIL);

        //when
        userService.addNewUser(userRequestDto);
        List<UserEntity> all = userRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void should_find_all_notifications_for_account_by_id() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(TECHNICAL_ID, CORRECT_NAME, CITY, CORRECT_EMAIL);
        userService.addNewUser(userRequestDto);
        List<UserEntity> all = userRepository.findAll();
        UUID technicalId = all.get(0).getTechnicalId();
        EventRequestDto event = new EventRequestDto("event", CITY, LocalDateTime.now());
        eventService.addEvent(event);
        //when
        List<NotificationResponceDto> allNotifications = userService.findAllNotifications(technicalId);
        //then
        assertThat(allNotifications.size()).isEqualTo(1);
    }

    private UserRequestDto prepareAccountRequestDto(UUID technicalId, String name, String city, String email) {
        return new UserRequestDto(technicalId, name, city, email);
    }
}