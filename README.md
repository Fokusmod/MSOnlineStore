
MSOnlineStore
=====================
Это курсовой проект, главной задачей которого было попробовать различные технолигии 
Spring фреймворка и не только. MSOnlineStore разработан на основе микросервисной архитектуры.
Всего на данный момент имеется 6 сервисов:
* Сервис Аутентификации
* Сервис Колл-центр
* Сервис для работы с корзиной покупателя
* Продуктовый сервис
* Сервис маршрутизации между микросервисами
* Сервис пользовательского интерфейса

Стек технологий
=====================
* Java 11
* Spring Boot
* Spring Data JPA
* Spring Security, JWT
* Spring AOP
* Spring Gateway
* PostgreSql, H2, Liquibase
* Redis, Kafka, WebSocket
* Open API (Swagger)
* Lombok, Apache poi
* Docker
* HTML, JS, CSS, AngularJS



Запуск дополнительных инструментов.
=====================
Для работы приложения требуется иметь установленный [Docker](https://www.docker.com/products/docker-desktop/).
В командной строке запустить контейнер.(Вы должны находиться в директории приложения так как файл docker-compose.yaml находится там)

    docker-compose up
   
Контейнер содержит в себе:
* PostgreSql;
* Redis;
* Kafka
