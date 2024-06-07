package com.example.cultural_event.subscription.service;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionReaderRepository;
import com.example.cultural_event.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionReaderServiceImpl implements SubscriptionReaderService {
    private final SubscriptionReaderRepository subscriptionReaderRepository;

    public SubscriptionReaderServiceImpl(SubscriptionReaderRepository subscriptionReaderRepository) {
        this.subscriptionReaderRepository = subscriptionReaderRepository;
    }

    @Override
    public List<SubscriptionEntity> findByEvent(EventEntity event) {
        return subscriptionReaderRepository.findByEvents(event);
    }

    @Override
    public List<UserEntity> findAllUsersByEvent(UUID eventId) {
        return subscriptionReaderRepository.findAllUsersByEvent(eventId);
    }
}
