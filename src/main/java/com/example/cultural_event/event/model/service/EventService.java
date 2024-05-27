package com.example.cultural_event.event.model.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;

import java.util.List;

public interface EventService {
    void addEvent(EventRequestDto eventRequestDto);

    List<EventResponseDto> findAllEvents();

    List<EventResponseDto> findAllEventsByCity(String city);
}