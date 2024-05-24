package com.example.cultural_event.service;

import com.example.cultural_event.model.dto.EventRequestDto;

public interface EventService {
    void addEvent(EventRequestDto eventRequestDto);
}