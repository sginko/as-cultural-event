package com.example.cultural_event.event.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.mapper.EventMapper;
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
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EventServiceImplTest {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

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

    @Test
    void should_delete_event_by_eventId() {
        //given
        String eventName = "Test Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();

        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);
        eventService.addEvent(eventRequestDto);
        List<EventResponseDto> allEvents = eventService.findAllEvents();
        UUID eventId = allEvents.get(0).getEventId();

        //when
        eventService.deleteByEventId(eventId);

        //then
        assertThat(eventRepository.findAll().isEmpty()).isTrue();
    }

    @Test
    void should_throw_exception_when_no_events_found_for_eventId() {
        //given
        UUID incorrectEventId = UUID.randomUUID();
        String eventName = "Test Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();

        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);
        eventService.addEvent(eventRequestDto);
        List<EventResponseDto> allEvents = eventService.findAllEvents();
        UUID eventId = allEvents.get(0).getEventId();
        eventService.deleteByEventId(eventId);

        //when
        Executable e = () -> eventService.deleteByEventId(incorrectEventId);

        //then
        assertThat(eventRepository.findAll().isEmpty()).isTrue();
    }

    @Test
    void should_update_event() {
        //given
        String eventName = "Test Event Name";
        String newEventName = "New Event Name";
        String city = "City";
        LocalDateTime dateTimeEvent = LocalDateTime.now();

        EventRequestDto eventRequestDto = new EventRequestDto(eventName, city, dateTimeEvent);
        eventService.addEvent(eventRequestDto);
        List<EventResponseDto> allEvents = eventService.findAllEvents();
        UUID eventId = allEvents.get(0).getEventId();

        EventRequestDto updatedEventRequestDto = new EventRequestDto(newEventName, city, dateTimeEvent);

        //when
        eventService.updateEvent(eventId, updatedEventRequestDto);
        //and
        Optional<EventEntity> byEventId = eventRepository.findByEventId(eventId);

        //then
        assertThat(byEventId.get().getEventName()).isEqualTo(newEventName);
    }
}