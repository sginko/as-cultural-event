package com.example.cultural_event.event.model.service;

import com.example.cultural_event.event.model.enity.EventEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventReaderService {

    Optional<EventEntity> findByEventId(UUID eventId);

    List<EventEntity> findAllByDateTimeEvent(LocalDateTime dateTimeEvent);
}