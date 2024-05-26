package com.example.cultural_event.account;

import com.example.cultural_event.event.model.enity.EventEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Set<String> listNotification;
    private String name;
    private String city;
    private String email;

    public AccountEntity(String name, String city, String email) {
        validateName(name);
        validateEmail(email);
        this.listNotification = new HashSet<>();
        this.name = name;
        this.city = city;
        this.email = email;
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

    public void receiveNotification(String eventName) {
        //System.out.println("Notification for account " + name + ": Event " + eventName + " has been created. In work...");
        listNotification.add("Notification about event " + eventName + " in your city " + city);
    }


}
