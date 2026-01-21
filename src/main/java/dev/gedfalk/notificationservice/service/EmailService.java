package dev.gedfalk.notificationservice.service;

import dev.gedfalk.notificationservice.dto.UserEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    public void sendNotificationEmail(UserEventDto event) {
        // TODO: заглушка для отправки email
        log.info("_____ Отправка Email ____");
        log.info(event.getEmail());
        log.info(event.getEmailTitle());
        log.info(event.getEmailMessage());
    }
}
