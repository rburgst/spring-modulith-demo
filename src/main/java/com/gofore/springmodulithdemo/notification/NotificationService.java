package com.gofore.springmodulithdemo.notification;

import com.gofore.springmodulithdemo.notification.internal.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}