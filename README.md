# Questionnaire-service

- rest-api интерфейс для проведения опросов и тестирования

[![Swagger Validator](src/main/resources/images/swagger-valid-brightgreen.svg)](http://localhost:8189/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

##Функциональные требования:

1. Должна быть возможность залогиниться под зарегистрированным пользователем, чтобы была возможность работы в системе нескольких пользователей.
2. Должна быть возможность добавить вопросы и ответы на них.
3. Вопросы могут быть 3х типов: со свободным вводом ответа, с выбором одного ответа из нескольких вариантов, выбор нескольких вариантов.
4. Должна быть возможность добавить от имени текущего пользователя ответ на вопрос с сохранением ответа в бд.
5. Система должна проверить введенный пользователем ответ на вопрос и сверить его с правильным ответов на этот вопрос. Результат проверки сохраняется в бд для конкретного пользователя.
6. Система должна позволять проводить анонимные опросы.


###Роли в приложении:
- Админ - права на модерацию + удаление топиков, вопросов + распределение прав;
- Менеджер - создание/редактирование своих тестов + просмотр статистики по тестам и по пользователям (не реализовано);
- Пользователь - прохождение тестов + просмотр индивидуальной статистики

###Технологии:
####Backend:
- Java 11
- Frameworks by minimization code:
    - Optionals
    - Stream API
    - Lombok
- Add library:
    - Swagger + UI
    - ObjectMapper
    - JWT
    - FlyWay
    - Hibernate
- Spring boot:
    - Data-jpa
    - Security
      Build:
- Data Base:
    - PostgreSQL;

## Установка
Для работы rest- сервиса требуется предустановленная база данных Postgres. 
При запуске приложения в базе данных автоматически разворачиваются таблицы, 
устанавливаются связи между ними, и происходит их наполнение данными.
Swagger UI позволяет посмотреть реализованные ендпойнты контроллеров,
с возможностью их тестирования.
Авторизация доступна по 3 учетным данным: admin/admin, manager/manager, guest/guest


####Repository source code:
- GitHub
