package com.example.cultural_event.event.model.mapper;

import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.dto.EventResponseDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventResponseDto fromEntity(EventEntity eventEntity){
        return new EventResponseDto(eventEntity.getEventName(), eventEntity.getCity(), eventEntity.getDateTimeEvent());
    }

    public EventEntity toEntity(EventRequestDto eventRequestDto){
        return new EventEntity(eventRequestDto.getEventName(), eventRequestDto.getCity(), eventRequestDto.getDateTimeEvent());
    }
}