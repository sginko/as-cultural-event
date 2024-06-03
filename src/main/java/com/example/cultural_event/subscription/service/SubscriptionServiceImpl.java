package com.example.cultural_event.subscription.service;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.account.service.AccountReaderService;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.service.EventException;
import com.example.cultural_event.event.model.service.EventReaderService;
import com.example.cultural_event.event.model.service.EventService;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final EventReaderService eventReaderService;
    private final AccountReaderService accountReaderService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, EventService eventService,
                                   EventReaderService eventReaderService, AccountReaderService accountReaderService) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventReaderService = eventReaderService;
        this.accountReaderService = accountReaderService;
    }

    @Override
    public void addSubscriptionForEvent(UUID eventId, UUID accountId) {
        EventEntity eventEntity = eventReaderService.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));

        AccountEntity accountEntity = accountReaderService.findByTechnicalId(accountId)
                .orElseThrow();

        SubscriptionEntity subscriptionEntity = new SubscriptionEntity(accountEntity, eventEntity);
        subscriptionRepository.save(subscriptionEntity);
    }
}