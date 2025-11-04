package com.gofore.springmodulithdemo.product;

import com.gofore.springmodulithdemo.notification.api.NotificationDTO;
import com.gofore.springmodulithdemo.notification.api.NotificationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductService {

    private final ApplicationEventPublisher events;

    public ProductService(ApplicationEventPublisher events, NotificationService notificationService) {
        this.events = events;
    }

    public void createSafe(Product product) {
        events.publishEvent(new NotificationDTO(new Date(), "SMS", product.getName()));
    }
}



