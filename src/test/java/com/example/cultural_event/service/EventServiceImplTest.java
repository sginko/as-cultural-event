package com.example.cultural_event.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.EventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        //given
        String eventName = "Test Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();
        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);

        //when
        eventService.addEvent(eventRequestDto);

        //then
        assertThat(eventRequestDto).isNotNull();
        assertThat(eventRequestDto.getEventName()).isEqualTo("Test Event Name");
        assertThat(eventRequestDto.getCity()).isEqualTo("City");
    }

    @Test
    void should_find_all_events() {
        //given
        String eventName = "Test Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();
        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);
        eventService.addEvent(eventRequestDto);

        //when
        List<EventResponseDto> allEvents = eventService.findAllEvents();

        //then
        assertThat(allEvents).isNotNull();
    }
}