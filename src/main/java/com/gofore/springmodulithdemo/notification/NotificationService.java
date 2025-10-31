package com.gofore.springmodulithdemo.notification;

import com.gofore.springmodulithdemo.notification.internal.Notification;
import com.gofore.springmodulithdemo.notification.internal.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
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
    public void notificationEvent(NotificationDTO event) {
        Notification notification = toEntity(event);
        LOG.info("Received safe notification by event for product {} in date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());
    }

    private Notification toEntity(NotificationDTO event) {
        return new Notification(event.getDate(), NotificationType.valueOf(event.getFormat()), event.getProductName());
    }
}