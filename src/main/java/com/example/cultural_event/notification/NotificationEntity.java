//package com.example.cultural_event.notification;
//
//import com.example.cultural_event.account.AccountEntity;
//import com.example.cultural_event.event.model.enity.EventEntity;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@Getter
//@Setter
//@Table(name = "events_accounts")
//public class NotificationEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private AccountEntity accounts;
//    @ManyToOne
//    @JoinColumn(name = "event_id")
//    private EventEntity events;
//
//    public NotificationEntity(AccountEntity accounts, EventEntity events) {
//        this.accounts = accounts;
//        this.events = events;
//    }
//}
