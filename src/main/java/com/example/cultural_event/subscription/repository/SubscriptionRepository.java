package com.example.cultural_event.subscription.repository;

import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
}
