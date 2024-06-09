package com.example.cultural_event.notification.service.notificationService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.service.eventReaderService.EventReaderService;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.service.SubscriptionReaderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulerService {
    private final Integer NUMBER_MINUTES_SAVING_NOTIFICATION = 60;
    private final EventReaderService eventReaderService;
    private final NotificationService notificationService;
    private final SubscriptionReaderService subscriptionReaderService;

    public SchedulerService(EventReaderService eventReaderService, NotificationServiceImpl notificationService, SubscriptionReaderService subscriptionReaderService) {
        this.eventReaderService = eventReaderService;
        this.notificationService = notificationService;
        this.subscriptionReaderService = subscriptionReaderService;
    }

    @Scheduled(fixedRate = 60000)
    public void sendEventNotifications() {
        LocalDateTime oneHourFromNow = LocalDateTime.now().plusHours(1).withSecond(0).withNano(0);
        List<EventEntity> upcomingEvents = eventReaderService.findAllByDateTimeEvent(oneHourFromNow);

        for (EventEntity event : upcomingEvents) {
            List<SubscriptionEntity> subscriptions = subscriptionReaderService.findByEvent(event);
            notificationService.sendNotificationsAboutUpcomingEvent(event, subscriptions);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void deleteNotificationAfterFinishedEvent() {

        List<NotificationEntity> expiredNotifications = notificationService.getAllExpiredNotifications(NUMBER_MINUTES_SAVING_NOTIFICATION);
        notificationService.deleteAllExpiredNotifications(expiredNotifications);

    }
}
