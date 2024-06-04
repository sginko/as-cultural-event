package com.example.cultural_event.event.model.repository;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventReaderRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findByEventId(UUID eventId);

    List<EventEntity> findAllEventsByDateTimeEvent(LocalDateTime dateTimeEvent);
    List<EventEntity> findByCity(String city);
}
