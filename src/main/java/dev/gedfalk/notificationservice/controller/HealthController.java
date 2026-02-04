package dev.gedfalk.notificationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Check", description = "Проверка базовой работоспособности user-notification API")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "Проверка 'здоровья' приложения")
    @ApiResponse(
            responseCode = "200",
            description = "Приложение работает корреткно"
    )
    public String health() {
        return "Всё чики-пуки!";
    }
}