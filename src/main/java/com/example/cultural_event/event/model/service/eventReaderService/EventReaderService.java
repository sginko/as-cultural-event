package com.example.cultural_event.event.model.service.eventReaderService;

import com.example.cultural_event.event.model.enity.EventEntity;

import java.util.List;

public interface EventReaderService {

    List<EventEntity> findByCity(String city);
}
