package com.example.cultural_event.user.entity;

import com.example.cultural_event.user.service.UserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String city;
    private String email;
    private UUID technicalId;

    public UserEntity(String name, String city, String email) {
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.city = city;
        this.email = email;
        this.technicalId = UUID.randomUUID();
    }

    private void validateEmail(String email) {
        if (!email.contains("@") || email.trim().contains(" ")) {
            throw new UserException("Check your email!");
        }
    }

    private void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new UserException("Name can not be empty!");
        }
    }

    public void receiveNotification(String eventName, String content) {
        System.out.println("Notification for user " + name + ": Event " + eventName + " " + content);
    }
}
