package dev.gedfalk.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {

    @NotBlank(message = "Требуется ввести почтовый ящик")
    @Email(message = "Некорректный формат почтового ящика")
    @Schema(description = "Почтовый ящик пользователя", example = "joerogan@gmail.com")
    private String emailTo;

    @NotBlank(message = "Требуется ввести тему письма")
    @Schema(description = "Тема письма", example = "Вам Бандероль!")
    private String title;

    @NotBlank(message = "Требуется ввести тело письма")
    @Schema(description = "Содержание письма", example = "Это содержание письма. Дело было так...")
    private String message;
}
