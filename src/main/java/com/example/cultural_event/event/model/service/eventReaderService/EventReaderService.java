package com.example.cultural_event.event.model.service.eventReaderService;

import com.example.cultural_event.event.model.enity.EventEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventReaderService {

    List<EventEntity> findByCity(String city);

    Optional<EventEntity> findByEventId(UUID eventId);

    @Query("SELECT e FROM EventEntity e " +
            "WHERE e.dateTimeEvent >= :date ")
    List<EventEntity> findAllByDateTimeEvent(LocalDateTime dateTimeEvent);
}
