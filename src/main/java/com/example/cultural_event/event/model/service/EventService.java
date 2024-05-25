package com.example.cultural_event.event.model.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;

import java.util.List;

public interface EventService {
    void addEvent(EventRequestDto eventRequestDto);
    List<EventResponseDto> findAllEvents();
}