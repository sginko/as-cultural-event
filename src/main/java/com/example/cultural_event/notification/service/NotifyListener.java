package com.example.cultural_event.notification.service;

import com.example.cultural_event.event.model.enity.EventEntity;

public interface NotifyListener {
    void notifyFromEvent(EventEntity event);
}
