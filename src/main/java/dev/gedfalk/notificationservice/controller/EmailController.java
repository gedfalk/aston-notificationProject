package dev.gedfalk.notificationservice.controller;

import dev.gedfalk.notificationservice.dto.EmailRequestDto;
import dev.gedfalk.notificationservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @Operation(
            summary = "Отправка письма",
            description = "Отправляет email-сообщение по указанному адресу. Требуется корректный адрес и текст сообщения"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email успешно отправлен"),
            @ApiResponse(responseCode = "400", description = "Не удалось отправить email. Некорректныые данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDto request) {
        try {
            emailService.sendCustomEmail(request.getEmailTo(), request.getTitle(), request.getMessage());
            return ResponseEntity.ok("Email успешно отправлен на адрес " + request.getEmailTo());
        } catch (Exception e) {
            log.error("Ошибка отправки email", e);
            return ResponseEntity.badRequest()
                    .body("Не удалось отправить email" + e.getMessage());
        }
    }

}
