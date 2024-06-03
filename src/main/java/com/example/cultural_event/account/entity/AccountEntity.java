package com.example.cultural_event.account.entity;

import com.example.cultural_event.account.service.AccountException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city;
    private String email;
    private UUID technicalId;

    public AccountEntity(String name, String city, String email) {
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.city = city;
        this.email = email;
        this.technicalId = UUID.randomUUID();
    }

    private void validateEmail(String email) {
        if (!email.contains("@") || email.trim().contains(" ")) {
            throw new AccountException("Check your email!");
        }
    }

    private void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new AccountException("Name can not be empty!");
        }
    }

    public void receiveNotification(String eventName, String content) {
        System.out.println("Notification for account " + name + ": Event " + eventName + " " + content);
    }
}