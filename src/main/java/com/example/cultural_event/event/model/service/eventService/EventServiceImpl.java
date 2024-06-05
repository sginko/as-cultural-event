package com.example.cultural_event.event.model.service.eventService;

import com.example.cultural_event.event.model.EventException;
import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.mapper.EventMapper;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.notification.model.service.notificationService.NotificationListener;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final NotificationListener notificationListener;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, NotificationListener notificationListener) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.notificationListener = notificationListener;
    }

    @Override
    @Transactional
    public void addEvent(EventRequestDto eventRequestDto) {
        EventEntity event = eventMapper.toEntity(eventRequestDto);
        eventRepository.save(event);
        notificationListener.notificationFromEvent(event);
    }

    @Override
    public List<EventResponseDto> findAllEvents() {
        List<EventResponseDto> eventList = eventRepository.findAll().stream()
                .map(eventEntity -> eventMapper.fromEntity(eventEntity))
                .toList();
        return eventList;
    }

    @Override
    public List<EventResponseDto> findAllEventsByCity(String city) {
        List<EventResponseDto> eventList = eventRepository.findAllEventsByCity(city.toLowerCase()).stream()
                .map(eventEntity -> eventMapper.fromEntity(eventEntity))
                .toList();
        return eventList;
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