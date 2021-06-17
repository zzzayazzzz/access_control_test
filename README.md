# access_control_test

Для создания базы данных из консоли:

`sudo -u postgres -i
psql
create database access_control`

Я использовал дефолтный username = postgres и password = postgres для подключения spring к postgres, эти значения можно поменять в /src/main/resources/application.properties

`spring.datasource.url=jdbc:postgresql://localhost:5432/access_control
spring.datasource.username=postgres
spring.datasource.password=postgres`

При запуске приложения выводится хэш пароля администратора для прямой вставки в таблицу админов

`INSERT INTO ADMINS (password, username) 
VALUES('$2a$10$wkyOrfask25V8LCOAAiJeuQFAeIBUTPfrsj1a9O1q2WhpIEh0bm.a','admin');`

login: admin

password: password

Swagger UI: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
