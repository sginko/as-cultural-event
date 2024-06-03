package com.example.cultural_event.notification.enity;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.event.model.enity.EventEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "event_id")
//    private EventEntity events;
//
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private AccountEntity account;

    private String notification;
    private String city;

    public NotificationEntity(String notification, String city) {
        this.notification = notification;
        this.city = city;
    }
}
