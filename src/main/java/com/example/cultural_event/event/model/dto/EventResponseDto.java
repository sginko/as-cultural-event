package com.example.cultural_event.event.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EventResponseDto {
    private UUID eventId;
    private String eventName;
    private String city;
    private LocalDateTime dateTimeEvent;

    public EventResponseDto(UUID eventId, String eventName, String city, LocalDateTime dateTimeEvent) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.city = city;
        this.dateTimeEvent = dateTimeEvent;
    }
}
