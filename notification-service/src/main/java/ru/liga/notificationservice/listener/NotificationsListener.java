package ru.liga.notificationservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationsListener {

    @RabbitListener(queues = {"queue1"})
    public void receiveNotification(String message) {
        log.info("Notification received: " + message);
    }
}
