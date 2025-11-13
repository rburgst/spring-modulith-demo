package com.gofore.springmodulithdemo.notification.api;

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

