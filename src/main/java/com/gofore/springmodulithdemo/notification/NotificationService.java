package com.gofore.springmodulithdemo.notification;

import com.gofore.springmodulithdemo.notification.internal.Notification;
import com.gofore.springmodulithdemo.notification.internal.NotificationType;
import com.gofore.springmodulithdemo.product.ProductCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    public void createNotification(Notification notification) {
        LOG.info("Received notification by module dependency for product {} in date {} by {}.",
          notification.getProductName(),
          notification.getDate(),
          notification.getFormat());
    }


    @EventListener
    public void notificationEvent(ProductCreated event) {
        Notification notification = createNotificationForProduct(event);
        LOG.info("Received product created event for product {} in date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());
    }

    private Notification createNotificationForProduct(ProductCreated event) {
        return new Notification(event.date(), NotificationType.SMS, event.productName());
    }
}