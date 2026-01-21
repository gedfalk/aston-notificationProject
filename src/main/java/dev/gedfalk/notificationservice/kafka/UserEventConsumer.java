package dev.gedfalk.notificationservice.kafka;

import dev.gedfalk.notificationservice.dto.UserEventDto;
import dev.gedfalk.notificationservice.service.EmailService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "userEvents", groupId = "notification-service-group")
    public void consume(UserEventDto event) {
        try {
            log.info("получили из Кафки event");
            emailService.sendNotificationEmail(event);
        } catch (Exception e) {
            log.error("Ну фигня какая-то", e);
        }
    }
}
