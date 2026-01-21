package dev.gedfalk.notificationservice.service;

import dev.gedfalk.notificationservice.dto.UserEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendNotificationEmail(UserEventDto event) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(event.getEmail());
            message.setSubject(event.getEmailTitle());
            message.setText(event.getEmailMessage());

            log.info("Отправляю письмо...");

            mailSender.send(message);

            log.info("Письмо успешно отправлено!");

        } catch (Exception e) {
            log.error("Ошибка при отправке письма", e);
        }
    }
}
