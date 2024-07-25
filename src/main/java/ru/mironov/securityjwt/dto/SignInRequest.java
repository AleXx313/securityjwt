package ru.mironov.securityjwt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {

    @Schema(description = "Имя пользователя", example = "Alex")
    @Size(min = 3, max = 15, message = "Имя пользователя должно содержать от 3 до 15 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Пароль", example = "password")
    @Size(min = 3, max = 15, message = "Длина пароля должна быть от 3 до 15 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
