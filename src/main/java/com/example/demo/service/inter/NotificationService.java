package com.example.demo.service.inter;

import com.example.demo.model.request.NotificationDto;

public interface NotificationService {

    void sendAsync(NotificationDto notificationDto);

    void send(NotificationDto notificationDto);
}
