package com.gofore.springmodulithdemo.product;

import com.gofore.springmodulithdemo.notification.NotificationDTO;
import com.gofore.springmodulithdemo.notification.NotificationService;
import com.gofore.springmodulithdemo.notification.internal.Notification;
import com.gofore.springmodulithdemo.notification.internal.NotificationType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductService {

//    private final NotificationService notificationService;
    private final ApplicationEventPublisher events;

    public ProductService(ApplicationEventPublisher events, NotificationService notificationService) {
        this.events = events;
//        this.notificationService = notificationService;
    }

//    public void create(Product product) {
//        notificationService.createNotification(new Notification(new Date(), NotificationType.SMS, product.getName()));
//    }

    public void createSafe(Product product) {
        events.publishEvent(new NotificationDTO(new Date(), "SMS", product.getName()));
    }
}