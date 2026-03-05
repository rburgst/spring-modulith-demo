package com.gofore.springmodulithdemo.notification.impl;

import com.gofore.springmodulithdemo.notification.api.NotificationDTO;
import com.gofore.springmodulithdemo.notification.api.NotificationService;
import com.gofore.springmodulithdemo.product.ProductCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void createNotification(NotificationDTO notification) {
        LOG.info("Received notification by direct module dependency for product {} at date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());
    }

    @EventListener
    public void notificationEvent(ProductCreated event) {
        Notification notification = createNotificationForProduct(event);
        LOG.info("Received ProductCreated notification by event for product {} at date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());
    }

    private Notification createNotificationForProduct(ProductCreated event) {
        return new Notification(event.createdAt(), NotificationType.SMS, event.productName());
    }
}



