#StockService

Сервис для получения информации по ценным бумагам с [Московской биржи](https://www.moex.com/ru/bondization/issuer) и [Tinkoff Invest API](https://www.tinkoff.ru/invest/open-api/).

Данный сервис предоставляет единый интефейс для взаимодействия с [MoexService](https://github.com/LeoMcKeloy/MoexService) и [TinkoffService](https://github.com/LeoMcKeloy/TinkoffService).

## Запуск на локальной машине

Для запуска потребуется установленный Docker for Desktop.
Dockerfile для создания образа и запуска контейнера сервиса находится в корне.

### build image
>docker build -t stock .

### run container
>docker run -p 8002:8002 --name stock-service -t stock

## Доступ к OpenAPI

[Open api](http://localhost:8002/swagger-ui.html)
