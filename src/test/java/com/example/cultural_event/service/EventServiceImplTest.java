package com.example.cultural_event.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.event.model.service.EventException;
import com.example.cultural_event.event.model.service.EventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(allEvents.size()).isEqualTo(1);
    }

    @Test
    void should_throw_exception_when_no_events_found() {
        // given


        // when
        Executable executable = () -> eventService.findAllEvents();

        // then
        assertThrows(EventException.class, executable, "Cannot find events");
    }

    @Test
    void should_find_all_events_by_city() {
        //given
        String eventName = "Test Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();
        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);
        eventService.addEvent(eventRequestDto);

        //when
        List<EventResponseDto> allEvents = eventService.findAllEventsByCity(city);

        //then
        assertThat(allEvents.get(0).getCity()).isEqualTo(city);
    }

    @Test
    void should_throw_exception_when_no_events_found_for_city() {
        // given
        String eventName = "Test Event Name";
        String city = "City";
        String incorrectCity = "incorrectCity";
        LocalDateTime dateTimeEvent = LocalDateTime.now();
        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);
        eventService.addEvent(eventRequestDto);

        // when
        Executable executable = () -> eventService.findAllEventsByCity(incorrectCity);

        // then
        assertThrows(EventException.class, executable, "Cannot find events for city: " + incorrectCity);
    }
}