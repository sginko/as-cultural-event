package com.example.cultural_event.event.model.service;

import com.example.cultural_event.notification.service.NotifyListener;
import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.mapper.EventMapper;
import com.example.cultural_event.event.model.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private NotifyListener notifyListener;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, NotifyListener notifyListener) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.notifyListener = notifyListener;
    }

    @Override
    @Transactional
    public void addEvent(EventRequestDto eventRequestDto) {
        EventEntity event = eventMapper.toEntity(eventRequestDto);
        eventRepository.save(event);
        notifyListener.notifyFromEvent(event);
    }

    @Override
    public List<EventResponseDto> findAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventEntity -> eventMapper.fromEntity(eventEntity))
                .toList();
    }
}