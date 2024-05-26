package com.example.cultural_event.event.model.service;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.mapper.EventMapper;
import com.example.cultural_event.event.model.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
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

    @Override
    public List<EventResponseDto> findAllEvents() {
        List<EventResponseDto> eventList = eventRepository.findAll().stream()
                .map(eventEntity -> eventMapper.fromEntity(eventEntity))
                .toList();
        if (eventList.isEmpty()) {
            throw new EventException("Cannot find events");
        } else {
            return eventList;
        }
    }

    @Override
    public List<EventResponseDto> findAllEventsByCity(String city) {
        List<EventResponseDto> eventList = eventRepository.findAllEventsByCity(city.toLowerCase()).stream()
                .map(eventEntity -> eventMapper.fromEntity(eventEntity))
                .toList();
        if (eventList.isEmpty()) {
            throw new EventException("Cannot find events for city: " + city);
        } else {
            return eventList;
        }
    }

    @Override
    @Transactional
    public void deleteByEventId(UUID eventId) {
        EventEntity eventEntity = eventRepository.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));
        eventRepository.deleteByEventId(eventEntity.getEventId());
    }

    @Override
    @Transactional
    public void updateEvent(UUID eventId, EventRequestDto eventRequestDto) {
        EventEntity eventEntity = eventRepository.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));

        eventEntity.setEventName(eventRequestDto.getEventName());
        eventEntity.setCity(eventRequestDto.getCity());
        eventEntity.setDateTimeEvent(eventRequestDto.getDateTimeEvent());
        eventRepository.save(eventEntity);
    }
}