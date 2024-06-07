package com.example.cultural_event.notification.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponseDto {

    private String notification;
    private String city;

    public NotificationResponseDto(String notification, String city) {
        this.notification = notification;
        this.city = city;
    }
}
