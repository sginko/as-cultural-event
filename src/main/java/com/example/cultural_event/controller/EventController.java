package com.example.cultural_event.controller;

import com.example.cultural_event.model.dto.EventRequestDto;
import com.example.cultural_event.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addEvent(@RequestBody EventRequestDto eventRequestDto) {
        eventService.addEvent(eventRequestDto);
    }
}