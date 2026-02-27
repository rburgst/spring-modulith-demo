package com.gofore.springmodulithdemo.notification.impl;

import com.gofore.springmodulithdemo.notification.api.NotificationDTO;
import com.gofore.springmodulithdemo.notification.api.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void createNotification(NotificationDTO notification) {
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



