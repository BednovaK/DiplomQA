# DiplomQA
 [План тестирования](https://github.com/BednovaK/DiplomQA/blob/4ab522d10ea94f6c875c4925518a47c318f0e405/Plan.md)


### Запуск приложения

Для запуска приложения необходим **Docker** 

**Примечание**: Приложение запускалось через Docker на локальной машине.

* склонировать репозиторий ```https://github.com/BednovaK/DiplomQA.git```
* запустить docker container ```docker-compose up```.
*   Дождаться пока контейнеры запустятся
* в терминале IntelliJ IDEA запустить SUT:
    - с использованием БД MySQL
      командой ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```
    - с использованием БД PostgreSQL
      командой ```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar```
* запустить автотесты командой:
    - для конфигурации БД MySql:  
      ```./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" ```
    - для конфигурации БД PostgreSQL:  
      ```./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" ```

* запустить отчеты командой:

```./gradlew allureReport (первоначальная команда)```

```./gradlew allureServe (запуск и открытие отчетов)```

* остановить SUT комбдинацией клавиш ```CTRL+C```

* Завершить работу контейнеров ```docker-compose down```
