package com.example.cultural_event.event.controller;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.service.eventService.EventService;
//import com.example.cultural_event.subscription.service.SubscriptionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;
//    private final SubscriptionServiceImpl subscriptionService;

    public EventController(EventService eventService) {//, SubscriptionServiceImpl subscriptionService) {
        this.eventService = eventService;
//        this.subscriptionService = subscriptionService;
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

    @PatchMapping("/update/{event_id}")
    public void updateByEventId(@PathVariable("event_id") UUID eventId, @RequestBody EventRequestDto eventRequestDto) {
        eventService.updateEvent(eventId, eventRequestDto);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/{event_id}/subscribe")
//    public void addSubscription(@PathVariable("event_id") UUID eventId, @RequestBody AccountRequestDto accountRequestDto){
//        subscriptionService.addSubscriptionForEvent(eventId, accountRequestDto.getTechnicalId());
//    }
}
