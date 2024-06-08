package com.example.cultural_event.notification.repository;

import com.example.cultural_event.notification.enity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationReaderRepository extends JpaRepository<NotificationEntity, Long> {

    @Query("SELECT n FROM NotificationEntity n WHERE LOWER(n.city) = (SELECT LOWER(a.city) FROM UserEntity a where a.technicalId=:technicalId)")
    List<NotificationEntity> getAllNotificationsForAccount(UUID technicalId);

    @Query("SELECT n FROM NotificationEntity n " +
            "JOIN EventEntity e ON n.eventTechnicalId = e.eventId " +
            "JOIN SubscriptionEntity s ON e.id = s.events.id")
    List<NotificationEntity> getAllNotificationsForAccountWithSubscription(UUID technicalId);
}
