package com.example.cultural_event.subscription.entity;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity events;

    public SubscriptionEntity(UserEntity user, EventEntity events) {
        this.user = user;
        this.events = events;
    }
}
