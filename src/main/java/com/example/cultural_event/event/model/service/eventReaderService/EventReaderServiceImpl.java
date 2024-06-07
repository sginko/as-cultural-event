package com.example.cultural_event.event.model.service.eventReaderService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventReaderServiceImpl implements EventReaderService {

    private final EventReaderRepository eventReaderRepository;

    public EventReaderServiceImpl(EventReaderRepository eventReaderRepository) {
        this.eventReaderRepository = eventReaderRepository;
    }

    @Override
    public List<EventEntity> findByCity(String city) {
        return eventReaderRepository.findByCity(city);
    }
}
