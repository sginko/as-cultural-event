package com.example.cultural_event.subscription.service;

import com.example.cultural_event.event.model.EventException;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.service.eventReaderService.EventReaderService;
import com.example.cultural_event.event.model.service.eventService.EventService;
import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.service.UserReaderService;

import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final EventReaderService eventReaderService;
    private final UserReaderService userReaderService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, EventService eventService,
                                   EventReaderService eventReaderService, UserReaderService userReaderService) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventReaderService = eventReaderService;
        this.userReaderService = userReaderService;
    }

    @Override
    public void addSubscriptionForEvent(UUID eventId, UUID userId) {
        SubscriptionEntity subscription = subscriptionRepository.findByEventIdUserId(eventId, userId);
        if (subscription != null) {
            throw new EventException("This subscription is already exist!");
        }

        EventEntity eventEntity = eventReaderService.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));

        UserEntity userEntity = userReaderService.findByTechnicalId(userId)
                .orElseThrow(() -> new EventException("User not found" + userId));

        SubscriptionEntity subscriptionEntity = new SubscriptionEntity(userEntity, eventEntity);
        subscriptionRepository.save(subscriptionEntity);
    }

    public void deleteSubscriptionForEvent(UUID eventId, UUID technicalId) {
        SubscriptionEntity subscriptionByTechnicalIdaAndEventId = subscriptionRepository.getSubscriptionByTechnicalIdaAndEventId(technicalId, eventId);
        try {
            subscriptionRepository.delete(subscriptionByTechnicalIdaAndEventId);
        } catch (RuntimeException e) {
            throw new EventException("Subscription not found");
        }
    }
}

