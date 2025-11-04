package com.gofore.springmodulithdemo.notification.api;

import org.springframework.context.event.EventListener;

public interface NotificationService {
    void createNotification(Object notification);
}

