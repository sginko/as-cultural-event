package com.example.cultural_event.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class EventResponseDto {
    private String eventName;
    private String city;
    private LocalDateTime dateTimeEvent;

    public EventResponseDto(String eventName, String city, LocalDateTime dateTimeEvent) {
        this.eventName = eventName;
        this.city = city;
        this.dateTimeEvent = dateTimeEvent;
    }
}