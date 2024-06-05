package com.example.cultural_event.notification.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponseDto {
    private UUID eventTechnicalId;
    private String notification;
    private String city;

    public NotificationResponseDto(UUID eventTechnicalId, String notification, String city) {
        this.eventTechnicalId = eventTechnicalId;
        this.notification = notification;
        this.city = city;
    }
}
