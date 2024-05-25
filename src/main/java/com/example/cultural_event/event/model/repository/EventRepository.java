package com.example.cultural_event.event.model.repository;

import com.example.cultural_event.event.model.enity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}