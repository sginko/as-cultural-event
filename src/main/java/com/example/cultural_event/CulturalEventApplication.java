package com.example.cultural_event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CulturalEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(CulturalEventApplication.class, args);
    }
}