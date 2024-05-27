package com.example.cultural_event.event.model.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    void addEvent(EventRequestDto eventRequestDto);

    List<EventResponseDto> findAllEvents();

    List<EventResponseDto> findAllEventsByCity(String city);

    void deleteByEventId(UUID eventId);

    void updateEvent(UUID eventId, EventRequestDto eventRequestDto);
}