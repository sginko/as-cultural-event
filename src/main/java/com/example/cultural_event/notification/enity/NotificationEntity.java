package com.example.cultural_event.notification.enity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private UUID eventTechnicalIg;

    private String notification;
    private String city;

    public NotificationEntity(UUID eventTechnicalIg, String notification, String city) {
        this.eventTechnicalIg = eventTechnicalIg;
        this.notification = notification;
        this.city = city;
    }
}
