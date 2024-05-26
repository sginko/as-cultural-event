package com.example.cultural_event.event.model.enity;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.notification.NotificationEntity;
import jakarta.persistence.*;
        import jakarta.validation.constraints.*;
        import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;
import org.springframework.context.annotation.Conditional;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50, message = "Event name should be between 3 and 50 characters")
    private String eventName;

    @NotBlank
    @Size(min = 2, max = 50, message = "City name should be between 2 and 50 characters")
    private String city;

    @NotNull(message = "The Date-Time field cannot be empty")
    private LocalDateTime dateTimeEvent;
    public EventEntity(String eventName, String city, LocalDateTime dateTimeEvent) {
        this.eventName = eventName;
        this.city = city;
        this.dateTimeEvent = dateTimeEvent;
    }
}