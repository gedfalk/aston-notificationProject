package dev.gedfalk.notificationservice;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import dev.gedfalk.notificationservice.dto.UserEventDto;
import dev.gedfalk.notificationservice.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmailServiceIntegrationTest {

    private GreenMail greenMail;

    @Autowired
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(3026, null, "smtp"));
        greenMail.start();
    }

    @AfterEach
    void tearDown() {
        if (greenMail != null) {
            greenMail.stop();
        }
    }

    @DynamicPropertySource
    static void configureMailProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.mail.host", () -> "localhost");
        registry.add("spring.mail.port", () -> 3026);
    }

    @Test
    void shouldSendWelcomeEmail() throws Exception {
        UserEventDto event = UserEventDto.builder()
                .eventType(UserEventDto.EventType.USER_CREATED)
                .email("eugene@gmail.com")
                .userName("Евгений Батькович")
                .build();

        emailService.sendNotificationEmail(event);

        greenMail.waitForIncomingEmail(5000, 1);

        MimeMessage[] messages = greenMail.getReceivedMessages();

        assertThat(messages).hasSize(1);
        assertThat(messages[0].getAllRecipients()[0].toString())
                .isEqualTo("eugene@gmail.com");
        assertThat(messages[0].getContent().toString())
                .contains("Ваш аккаунт в AstonProject был успешно создан");
    }

    @Test
    void shouldSendCustomEmail() throws Exception {
        String to = "xxx@gmail.com";
        String subject = "Тема такая себе";
        String message = "Сообщение тоже ни о чём";

        emailService.sendCustomEmail(to, subject, message);

        greenMail.waitForIncomingEmail(5000, 1);

        MimeMessage[] messages = greenMail.getReceivedMessages();

        assertThat(messages).hasSize(1);
        assertThat(messages[0].getAllRecipients()[0].toString())
                .isEqualTo(to);
        assertThat(messages[0].getSubject()).isEqualTo(subject);
        assertThat(messages[0].getContent().toString()).isEqualTo(message);
    }
}