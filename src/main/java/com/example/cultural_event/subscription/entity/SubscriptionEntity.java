package com.example.cultural_event.subscription.entity;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.event.model.enity.EventEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "subscriptions_accounts_events")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity events;

    public SubscriptionEntity(AccountEntity account, EventEntity events) {
        this.account = account;
        this.events = events;
    }
}