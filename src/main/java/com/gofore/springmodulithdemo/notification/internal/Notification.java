package com.gofore.springmodulithdemo.notification.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Notification {
    private Date date;
    private NotificationType format;
    private String productName;
}
