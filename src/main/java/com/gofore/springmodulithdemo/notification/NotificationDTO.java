package com.gofore.springmodulithdemo.notification;

import com.gofore.springmodulithdemo.notification.internal.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class NotificationDTO {
    private Date date;
    private String format;
    private String productName;
}
