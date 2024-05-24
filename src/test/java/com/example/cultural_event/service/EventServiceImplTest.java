package com.example.cultural_event.service;

import com.example.cultural_event.model.dto.EventRequestDto;
import com.example.cultural_event.repository.EventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceImplTest {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @AfterEach
    void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    void should_create_new_event() {
        String eventName = "Test Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();

        //given
        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);

        //when
        eventService.addEvent(eventRequestDto);

        //then
        assertThat(eventRequestDto).isNotNull();
        assertThat(eventRequestDto.getEventName()).isEqualTo("Test Event Name");
    }
}