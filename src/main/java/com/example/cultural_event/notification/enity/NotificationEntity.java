package com.example.cultural_event.notification.enity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID eventTechnicalId;
    private String notification;
    private String city;
    private LocalDateTime timeCreatingNotification;
    private boolean notificationSent;

    public NotificationEntity(UUID eventTechnicalId, String notification, String city) {
        this.eventTechnicalId = eventTechnicalId;
        this.notification = notification;
        this.city = city;
        this.timeCreatingNotification = LocalDateTime.now();
        this.notificationSent = false;
    }
}
