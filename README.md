# Spring JWT practice
## Практическое задание по теме Spring Security JWT

### Описание задания
В рамках задания необходимо было создать базовое веб-приложение с использованием Spring Security и JWT для аутентификации и авторизации пользователей.

### Описание проекта
Было создано приложение для регистрации, аутентификации и авторизации пользователей.
Аутентификация осуществляется при помощи механизма выдачи JWT токена.
В базовой реализации присутствуют 2 роли пользователей - USER и ADMIN.
<br>
<br>Подробная документация доступна по ссылке: http://localhost:8080/swagger-ui/index.html <br>
OAS доступна по ссылке: http://localhost:8080/v3/api-docs

### Тесты
Подготовлены тесты демо контроллера приложения проверяющие корректность предоставления доступа к данным пользователям в зависимости и их роли.
Подготовлены тесты сервиса аутентификации проверяющие корректность выдаваемых сервисом токенов.

### Запуск
Для запуска необходимо создать базу данных в PostgreSQL.
По умолчанию база данных должна быть доступна по адресу jdbc:postgresql://localhost:5432/securityjwt <br>
Логин пользователя: postgres <br>
Пароль: root

Параметры базы данных могут быть изменены в файле настройки application.yaml

### Используемые технологии
+ [Java](https://www.java.com/) (21)
+ [Spring Boot](https://spring.io/projects/spring-boot) (3)
+ [Spring Security](https://spring.io/projects/spring-security)
+ [Hibernate](https://hibernate.org)
+ [PostgreSQL](https://www.postgresql.org)
+ [Apache Maven](https://maven.apache.org)
+ [Project Lombok](https://projectlombok.org)