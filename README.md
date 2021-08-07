# Дипломный проект профессии «Тестировщик»

## Тестовая документация
[План автоматизации](https://github.com/Aleksey-Bur/DiplomQA/blob/master/Documents/Plan.md)
## **Процедура запуска авто-тестов**

##### Для запуска автотестов необходимо:
1. склонировать репозиторий командой `git clone`;
2. с официального сайта https://hub.docker.com/ скачать образы MySql, PostgreSQL и Node.js с помощью команды `docker pull <имя_образа>` (необходим установленный Docker);
3. запустить контейнер с MySql, PostgreSQL и Node.js с помощью команды `docker-compose up -d --build`;
4. запустить приложение:
* для использования БД MySQL запустить команду `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`;
* для использования БД PostgreSQL запустить команду `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`;
5. запустить автотесты:
* при использовании БД MySQL запустить команду `gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean tests`;
* при использовании БД PostgreSQL запустить команду `gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean tests`;
6. Для формирования отчета в трминале ввести команду `gradlew allureServe`. Отчет автоматически откроется в браузере.
7. после завершения автотестов необходимо остановить контейнеры с помощью команды `docker-compose down`.