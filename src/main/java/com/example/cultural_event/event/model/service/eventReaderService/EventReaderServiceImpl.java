package com.example.cultural_event.event.model.service.eventReaderService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventReaderServiceImpl implements EventReaderService {

    private final EventReaderRepository eventReaderRepository;

    public EventReaderServiceImpl(EventReaderRepository eventReaderRepository) {
        this.eventReaderRepository = eventReaderRepository;
    }

    @Override
    public Optional<EventEntity> findByEventId(UUID eventId) {
        return eventReaderRepository.findByEventId(eventId);
    }

    @Override
    public List<EventEntity> findAllByDateTimeEvent(LocalDateTime dateTimeEvent) {
        return eventReaderRepository.findAllEventsByDateTimeEvent(dateTimeEvent);
    }
}
