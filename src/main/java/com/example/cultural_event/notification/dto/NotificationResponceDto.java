package com.example.cultural_event.notification.dto;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.event.model.enity.EventEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponceDto {
//    private EventEntity events;
//    private AccountEntity account;
    private String notification;
    private String city;

    public NotificationResponceDto(String notification, String city) {
        this.notification = notification;
        this.city = city;
    }
}
