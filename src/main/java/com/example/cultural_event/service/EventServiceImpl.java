package com.example.cultural_event.service;

import com.example.cultural_event.model.dto.EventRequestDto;
import com.example.cultural_event.model.mapper.EventMapper;
import com.example.cultural_event.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
   private final EventRepository eventRepository;
   private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public void addEvent(EventRequestDto eventRequestDto) {
        eventRepository.save(eventMapper.toEntity(eventRequestDto));
    }
}