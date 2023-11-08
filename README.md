# Food delivery service

## Как запустить проект? 

### 1. liquibase

Для создания таблиц и заполнения их тестовыми данными необходимо запустить плагин **liquibase:update**

### 2. RabbitMQ

Для корректной работы сервисов необходим контейнер **RabbitMQ**:

**docker run -lt --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management**

### 3. Сервисы аутентификации

Для работы аутентификации необходимо запустить сначала **AuthenticationService**, а затем **CloudGateway**

### 4. Основные сервисы

Для корректной работы RabbitMQ сначала необходимо запустить **delivery-service**, **kitchen-service** и **order-service**, после успешного запуска этих сервисов запустить **notification-service**

## Схема движения заказа по сервисам

![image](https://github.com/YuriyShorin/ru-liga-food-service/assets/117041301/1ff079d7-61dc-4fb4-8033-aeb0f7efaa76)

## Авторизация

**username:** user

**password:** password

## delivery-service

http://127.0.0.1:8080/delivery-service/

## kitchen-service 

http://127.0.0.1:8080/kitchen-service/

## order-service 

http://127.0.0.1:8080/order-service/



