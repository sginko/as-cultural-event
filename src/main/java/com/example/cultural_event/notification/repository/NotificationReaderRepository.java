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
    @Query("SELECT n FROM NotificationEntity n WHERE n.city = (SELECT a.city FROM AccountEntity a where a.technicalId=:technicalId)")
    //"SELECT n FROM NotificationEntity n WHERE n.city = (SELECT a.city FROM AccountEntity a WHERE a.city = :city)"
    List<NotificationEntity> getAllNotificationsForAccount(UUID technicalId);
//
    @Transactional
    //@Query("SELECT n FROM NotificationEntity n WHERE n.account.technicalId = :technicalId AND EXISTS (SELECT s FROM SubscriptionEntity s WHERE s.account=n.account AND s.events=n.events)")
    @Query("SELECT 1")
   List<NotificationEntity> getAllNotificationsForAccountWithSubscription(UUID technicalId);
}
