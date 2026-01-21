package dev.gedfalk.notificationservice.dto;

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
    private String emailTo;

    @NotBlank(message = "Требуется ввести тему письма")
    private String title;

    @NotBlank(message = "Требуется ввести тело письма")
    private String message;
}
