package com.example.cultural_event.event.controller;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventRequestDto addEvent(@RequestBody EventRequestDto eventRequestDto) {
        eventService.addEvent(eventRequestDto);
        return eventRequestDto;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<EventResponseDto> findAllEvents() {
        return eventService.findAllEvents();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(params = "city")
    public List<EventResponseDto> findAllEventsByCity(@RequestParam String city) {
        return eventService.findAllEventsByCity(city);
    }

    @DeleteMapping("{event_id}")
    public void deleteByEventId(@PathVariable("event_id") UUID eventId) {
        eventService.deleteByEventId(eventId);
    }

    @PutMapping("/update/{event_id}")
    public void updateByEventId(@PathVariable("event_id") UUID eventId, @RequestBody EventRequestDto eventRequestDto) {
        eventService.updateEvent(eventId, eventRequestDto);
    }
}