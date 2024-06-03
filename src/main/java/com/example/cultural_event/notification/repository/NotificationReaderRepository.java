package com.example.cultural_event.notification.repository;

import com.example.cultural_event.notification.enity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationReaderRepository extends JpaRepository<NotificationEntity, Long> {
    @Transactional
    @Query("SELECT n FROM NotificationEntity n WHERE n.account.technicalId = :technicalId")
    List<NotificationEntity> getAllNotificationsForAccount(UUID technicalId);

    @Transactional
    @Query("SELECT n FROM NotificationEntity n WHERE n.account.technicalId = :technicalId AND EXISTS (SELECT s FROM SubscriptionEntity s WHERE s.account=n.account AND s.events=n.events)")
    List<NotificationEntity> getAllNotificationsForAccountWithSubscription(UUID technicalId);
}
