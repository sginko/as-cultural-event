package com.example.cultural_event.subscription.repository;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionReaderRepository extends JpaRepository<SubscriptionEntity, Long> {
    List<SubscriptionEntity> findByEvents(EventEntity event);
}