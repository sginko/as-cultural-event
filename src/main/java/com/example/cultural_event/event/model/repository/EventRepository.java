package com.example.cultural_event.event.model.repository;

import com.example.cultural_event.event.model.enity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e WHERE LOWER(e.city) = LOWER(:city)")
    List<EventEntity> findAllEventsByCity(String city);
}