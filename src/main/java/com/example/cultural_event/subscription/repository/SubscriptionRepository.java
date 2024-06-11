package com.example.cultural_event.subscription.repository;

import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Query("SELECT s FROM SubscriptionEntity s WHERE s.user.technicalId = :technicalId AND s.events.eventId = :eventId")
    SubscriptionEntity getSubscriptionByTechnicalIdaAndEventId(UUID technicalId, UUID eventId);

    @Query("SELECT s FROM SubscriptionEntity s " +
            "JOIN s.user u " +
            "JOIN s.events e " +
            "WHERE e.eventId = :eventId " +
            "AND u.technicalId = :userId")
    SubscriptionEntity findByEventIdUserId(UUID eventId, UUID userId);
}
