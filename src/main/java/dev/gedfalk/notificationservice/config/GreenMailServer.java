package dev.gedfalk.notificationservice.config;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class GreenMailServer {

    private GreenMail greenMail;

    @PostConstruct
    public void start() {
        greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.start();
        System.out.println("GreenMail запущен на порту 3025");
    }

    @PreDestroy
    public void stop() {
        if (greenMail != null) {
            greenMail.stop();
            System.out.println("GreenMail остановлен");
        }
    }
}
