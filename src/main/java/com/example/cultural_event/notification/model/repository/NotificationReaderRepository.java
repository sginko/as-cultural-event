package com.example.cultural_event.notification.model.repository;

import com.example.cultural_event.notification.model.enity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationReaderRepository extends JpaRepository<NotificationEntity, Long> {

    @Query("SELECT n FROM NotificationEntity n WHERE LOWER(n.city) = (SELECT LOWER(a.city) FROM UserEntity a where a.technicalId=:technicalId)")
    List<NotificationEntity> getAllNotificationsForAccount(UUID technicalId);
}
