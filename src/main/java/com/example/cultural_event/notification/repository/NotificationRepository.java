package com.example.cultural_event.notification.repository;

import com.example.cultural_event.notification.enity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    //@Query("SELECT n FROM NotificationEntity n WHERE n.timeCreatingNotification < (n.timeCreatingNotification + numberMinutes*60)")
    //List<NotificationEntity> getAllExpiredNotifications(Integer numberMinutes);
    @Query("SELECT n FROM NotificationEntity n WHERE n.timeCreatingNotification < :timeThreshold")
    List<NotificationEntity> getAllExpiredNotifications(@Param("timeThreshold") LocalDateTime timeThreshold);
}
