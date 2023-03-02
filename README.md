#StockService

Сервис для получения информации по ценным бумагам с [Московской биржи](https://www.moex.com/ru/bondization/issuer) и [Tinkoff Invest API](https://www.tinkoff.ru/invest/open-api/).

Данный сервис предоставляет единый интефейс для взаимодействия с [MoexService](https://github.com/LeoMcKeloy/MoexService) и [TinkoffService](https://github.com/LeoMcKeloy/TinkoffService).
Необходимо развернуть оба сервиса для работы со StockService.

## Создание docker-образа

Для запуска потребуется установленный Docker for Desktop.
Dockerfile для создания образа и запуска контейнера сервиса находится в корне.

>docker build . -t stock:0.0.1

## Запуск изолированного микросервиса в контейнере

>docker run -p 8002:8002 --name stock-service -t stock

## Запуск всех микросервисов и базы пользователей для аутентификации в контейнерах с помощью docker-compose

Перейти в папку db
>cd db

Запустить команду в терминале
>docker-compose up

## Доступ к OpenAPI

[Open api](http://localhost:8002/swagger-ui.html)
