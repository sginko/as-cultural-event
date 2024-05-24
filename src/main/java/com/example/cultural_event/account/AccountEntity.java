package com.example.cultural_event.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city;
    private String email;

    public AccountEntity(String name, String city, String email) {
        validateName(name);
        validateEmail(email);
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
}
