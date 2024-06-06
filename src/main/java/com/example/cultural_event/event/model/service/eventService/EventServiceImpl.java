package com.example.cultural_event.event.model.service.eventService;

import com.example.cultural_event.event.model.EventException;
import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.mapper.EventMapper;
import com.example.cultural_event.event.model.repository.EventRepository;
import com.example.cultural_event.notification.service.notificationService.NotificationListener;
import com.example.cultural_event.subscription.service.SubscriptionReaderService;
import com.example.cultural_event.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final NotificationListener notificationListener;
    private final SubscriptionReaderService subscriptionReaderService;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, NotificationListener notificationListener, SubscriptionReaderService subscriptionReaderService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.notificationListener = notificationListener;
        this.subscriptionReaderService = subscriptionReaderService;
    }

    @Override
    @Transactional
    public void addEvent(EventRequestDto eventRequestDto) {
        EventEntity event = eventMapper.toEntity(eventRequestDto);
        eventRepository.save(event);
        notificationListener.notificationAboutCreationEvent(event);
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
        EventEntity event = eventRepository.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));
        List<UserEntity> allUsersByEvent = subscriptionReaderService.findAllUsersByEvent(eventId);
        notificationListener.notificationAboutDeletionEvent(event, allUsersByEvent);
        eventRepository.deleteByEventId(event.getEventId());
    }

    @Override
    @Transactional
    public void updateEvent(UUID eventId, EventRequestDto eventRequestDto) {
        EventEntity event = eventRepository.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));

        event.setEventName(eventRequestDto.getEventName());
        event.setCity(eventRequestDto.getCity());
        event.setDateTimeEvent(eventRequestDto.getDateTimeEvent());
        eventRepository.save(event);
    }
}
