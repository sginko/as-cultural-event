package com.example.cultural_event.event.model.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;

public interface EventService {
    void addEvent(EventRequestDto eventRequestDto);
}