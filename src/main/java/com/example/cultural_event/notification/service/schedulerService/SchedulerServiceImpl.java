package com.example.cultural_event.notification.service.schedulerService;

import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.service.eventReaderService.EventReaderService;
import com.example.cultural_event.notification.enity.NotificationEntity;
import com.example.cultural_event.notification.service.notificationService.NotificationService;
import com.example.cultural_event.notification.service.notificationService.NotificationServiceImpl;
import com.example.cultural_event.subscription.entity.SubscriptionEntity;
import com.example.cultural_event.subscription.service.SubscriptionReaderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class SchedulerServiceImpl implements SchedulerService {
    private final Integer NUMBER_MINUTES_SAVING_NOTIFICATION = 1;
    private final EventReaderService eventReaderService;
    private final NotificationService notificationService;
    private final SubscriptionReaderService subscriptionReaderService;

    public SchedulerServiceImpl(EventReaderService eventReaderService, NotificationServiceImpl notificationService, SubscriptionReaderService subscriptionReaderService) {
        this.eventReaderService = eventReaderService;
        this.notificationService = notificationService;
        this.subscriptionReaderService = subscriptionReaderService;
    }
    @Override
    @Scheduled(fixedRate = 60000)
    public void sendEventNotifications() {
        LocalDateTime oneHourFromNow = LocalDateTime.now().plusHours(1).withSecond(0).withNano(0);
        List<EventEntity> upcomingEvents = eventReaderService.findAllByDateTimeEvent(oneHourFromNow);

        for (EventEntity event : upcomingEvents) {
            List<SubscriptionEntity> subscriptions = subscriptionReaderService.findByEvent(event);
            notificationService.sendNotificationsAboutUpcomingEvent(event, subscriptions);
        }
    }
    @Override
    @Scheduled(fixedRate = 60000)
    public void deleteNotificationAfterFinishedEvent() {
        List<NotificationEntity> expiredNotifications = notificationService.getAllExpiredNotifications(NUMBER_MINUTES_SAVING_NOTIFICATION);
        System.out.println(expiredNotifications);
        notificationService.deleteAllExpiredNotifications(expiredNotifications);
    }
}
