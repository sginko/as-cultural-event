package com.example.cultural_event.subscription.service;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.account.repository.AccountReader;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.repository.EventReaderRepository;
import com.example.cultural_event.event.model.service.EventException;
import com.example.cultural_event.event.model.service.EventService;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final EventReaderRepository eventReaderRepository;
    private final AccountReader accountReader;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, EventService eventService, EventReaderRepository eventReaderRepository, AccountReader accountReader) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventReaderRepository = eventReaderRepository;
        this.accountReader = accountReader;
    }

    @Override
    public void addSubscriptionForEvent(UUID eventId, UUID accountId) {
        EventEntity eventEntity = eventReaderRepository.findByEventId(eventId)
                .orElseThrow(() -> new EventException("Event for id: " + eventId + " not found"));

        AccountEntity accountEntity = accountReader.findByTechnicalId(accountId)
                .orElseThrow();

        SubscriptionEntity subscriptionEntity = new SubscriptionEntity(accountEntity, eventEntity);
        subscriptionRepository.save(subscriptionEntity);
    }
}
