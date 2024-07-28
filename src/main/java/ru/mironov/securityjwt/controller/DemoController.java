package ru.mironov.securityjwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
@Tag(name = "Приватный сервис для получения защищенных ресурсов")
public class DemoController {

    @GetMapping
    @Operation(summary = "Доступ разрешен только авторизованным пользователям")
    public String getUserGreetings() {
        return "Hello, User!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступ разрешен только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminGreetings() {
        return "Hello, Admin!";
    }
}
