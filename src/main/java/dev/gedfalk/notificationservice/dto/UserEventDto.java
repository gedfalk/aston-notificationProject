package dev.gedfalk.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDto {

    public enum EventType {
        USER_CREATED,
        USER_DELETED
    }

    private EventType eventType;
    private String email;
    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventTimestamp;

    private Integer userId;

    public String getEmailTitle() {
        switch (eventType) {
            case USER_CREATED:
                return "Вас зарегистрировали в AstonProject!";
            case USER_DELETED:
                return "Ваш аккаунт был успешно удалён";
            default:
                return "Что-то пошло не так...";
        }
    }

    public String getEmailMessage() {
        String safeUserName = userName == null ? "" : userName;
        switch (eventType) {
            case USER_CREATED:
                return String.format("Здравствуйте, %s! Ваш аккаунт в AstonProject был успешно создан.",
                        safeUserName);
            case USER_DELETED:
                return String.format("Здравствуйте, %s! Ваш аккаунт был удалён.");
            default:
                return "Пу-пу-пу...";
        }
    }
}
