package com.example.cultural_event.event.model.repository;

import com.example.cultural_event.event.model.enity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventReaderRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByEventId(UUID eventId);
}