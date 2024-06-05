package com.example.cultural_event.subscription.service;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.user.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface SubscriptionReaderService {
    List<SubscriptionEntity> findByEvent(EventEntity event);

    List<UserEntity> findAllUsersByEvent(UUID eventId);
}
