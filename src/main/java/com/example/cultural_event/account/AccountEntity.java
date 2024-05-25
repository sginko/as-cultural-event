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
        this.name = name;
        this.city = city;
        this.email = email;
    }
}
