package com.example.cultural_event.subscription.repository;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SubscriptionReaderRepository extends JpaRepository<SubscriptionEntity, Long> {

    List<SubscriptionEntity> findByEvents(EventEntity event);

    @Query("SELECT s.user FROM SubscriptionEntity s WHERE s.events.eventId = :eventId")
    List<UserEntity> findAllUsersByEvent(UUID eventId);
}
