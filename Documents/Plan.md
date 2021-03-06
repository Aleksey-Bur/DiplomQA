### **План автоматизации**

Провести функциональное тестирование сервиса покупки тура "Путешествие дня" 
по определенной цене, взаимодействующего с СУБД и API Банка, с помощью двух способов:
*обычная оплата по дебетовой карте
*выдача кредита по данным банковской карты
Автоматизация тестирования функциональности веб-сервиса при покупке туров:
*с использованием дебетовой карты
*с использованием кредита по данным банковской карты

**Тестовые данные**

* Корректными данными при заполнении полей ввода формы считаются:

* Номер карты: цифры, 16 шт., в формате **** **** **** ****

* Месяц: 01-12, но не ранее текущего месяца в случае, если указан текущий год

* Год: последние две цифры порядкового номера года, не ранее текущего года, не более 5-ти лет от текущего года

* Владелец: буквенные символы латинского алфавита

* CVC: цифры, 3 шт.

**Набор карт, представленный для тестирования (файл data.json):**

* 4444 4444 4444 4441, status APPROVED

* 4444 4444 4444 4442, status DECLINED

**Несуществующая карта** 

* 7777 7777 7777 7777

**Проверка поддержки заявленных СУБД:**

* MySql

* PostgreSQL

### **Тестовые сценарии**

**Позитивные сценарии**

_Оплата по карте со статусом APPROVED_

а) оплата по дебетовой карте 
Номер карты 4444 4444 4444 4441, остальные поля заполнены корректными данными. Ожидаемый результат: появилось всплывающее окно "Операция одобрена Банком", в БД в payment_entity появилась запись со статусом APPROVED

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, остальные поля заполнены корректными данными
Ожидаемый результат: появилось всплывающее окно "Операция одобрена Банком", в БД в credit_request_entity появилась запись со статусом APPROVED

**Негативные сценарии**

_Оплата по карте со статусом DECLINED_

а)оплата по заблокированной дебетовой карте
Номер карты 4444 4444 4444 4442, остальные поля заполнены корректными данными
Ожидаемый результат: появилось всплывающее окно "Ошибка! Банк отказал в проведении операции", в БД в payment_entity появилась запись со статусом DECLINED

б)оплата по заблокированной кредитной карте
Номер карты 4444 4444 4444 4442, остальные поля заполнены корректными данными
Ожидаемый результат: появилось всплывающее окно "Ошибка! Банк отказал в проведении операции",в БД в credit_request_entity появилась запись со статусом DECLINED

_Несуществующая карта_

а)оплата по дебетовой карте
Номер карты 7777 7777 7777 7777, остальные поля заполнены корректными данными
Ожидаемый результат: появилось всплывающее окно "Ошибка! Банк отказал в проведении операции", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 7777 7777 7777 7777, остальные поля заполнены корректными данными
Ожидаемый результат: появилось всплывающее окно "Ошибка! Банк отказал в проведении операции", в БД в credit_request_entity новая запись не появилась

_Неполный номер карты_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 444, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Номер карты появилось сообщение "Неверно указан формат карты", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 444, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Номер карты появилось сообщение "Неверно указан формат карты", в БД в credit_request_entity новая запись не появилась

_Карта с истекшим сроком действия (месяц)_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441, указать предыдущий месяц, текущий год, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Месяц появилось сообщение об ошибке "Неверно указан срок действия карты", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, указать предыдущий месяц, текущий год, остальные поля заполнены корректными значениями
Ожидаемый результат: под полем Месяц появилось сообщение об ошибке "Неверно указан срок действия карты", в БД в credit_request_entity новая запись не появилась

_Некорректный месяц_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441, в поле Месяц указать 00, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Месяц появилось сообщение об ошибке "Неверно указан срок действия карты", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, в поле Месяц указать 00, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Месяц появилось сообщение об ошибке "Неверно указан срок действия карты", в БД в credit_request_entity новая запись не появилась

_Карта с истекшим сроком действия (год)_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441, указать предыдущий год, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Год появилось сообщение об ошибке "Истёк срок действия карты", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, указать предыдущий год, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Год появилось сообщение об ошибке "Истёк срок действия карты", в БД в credit_request_entity новая запись не появилась

_Некорректный год_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441, в поле Год указать предыдущий год, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Год появилось сообщение об ошибке "Неверно указан срок действия карты", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, в поле Год указать предыдущий, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Год появилось сообщение об ошибке "Неверно указан срок действия карты", в БД в credit_request_entity новая запись не появилась

_Некорректное значение в поле Владелец_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441, в поле Владелец указать некорректные данные, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Владелец появилось сообщение об ошибке, в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, в поле Владелец указать некорректные данные, остальные поля заполнены корректными данными
Ожидаемый результат: под полем Владелец появилось сообщение об ошибке, в БД в credit_request_entity новая запись не появилась

_Некорректное значение в поле CVC/CVV_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441, в поле CVC/CVV указать 0, остальные поля заполнены корректными данными
Ожидаемый результат: под полем CVC/CVV появилось сообщение об ошибке "Неверно указан CVC/CVV", в БД в payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441, в поле CVC/CVV указать 0, остальные поля заполнены корректными данными
Ожидаемый результат: под полем CVC/CVV появилось сообщение об ошибке "Неверно указан CVC/CVV", в БД в credit_request_entity новая запись не появилась

_Отправка с незаполненными полями_

а)оплата по дебетовой карте
Номер карты 4444 4444 4444 4441,у полей ввода "Номер карты", "Месяц", "Год", "CVC/CVV" появляются сообщения об ошибке "Неверный формат", у поля "Владелец" сообщение об ошибке "Поле обязательно для заполнения". Заявка не отправляется.в БД в credit_request_entity/payment_entity новая запись не появилась

б)оплата по кредитной карте
Номер карты 4444 4444 4444 4441,у полей ввода "Номер карты", "Месяц", "Год", "CVC/CVV" появляются сообщения об ошибке "Неверный формат", у поля "Владелец" сообщение об ошибке "Поле обязательно для заполнения". Заявка не отправляется.в БД в credit_request_entity/payment_entity новая запись не появилась

**Перечень используемых инструментов с обоснованием выбора**

1. IntelliJ IDEA - специальная среда разработки, необходимая для оптимизации работы;
2. Java 11 - язык программирования для написания автотестов;
3. Gradle - инструмент автоматизации сборки и управления зависимостями;
4. JUnit5 — платформа для написания авто-тестов и их запуска;
5. Selenide - фреймворк для автоматизированного тестирования веб-приложений на основе Selenium WebDriver, более удобный и простой в использовании, чем Selenium;
6. Docker Compose - инструмент, позволяющий запускать мультиконтейнерные приложения, чтобы не устанавливать на компьютер необходимые для работы приложения Node.js и СУБД;
7. Allure - фреймворк, предназначенный для создания отчетов, более наглядных, чем у Gradle;
8. Lombok- библиотека для сокращения количества шаблонного кода, для объявления локальной переменной вместо указания реального типа;
9. Git и GitHub для хранения кода. Git достаточно прост и удобен для управления исходным кодом, очень распространенная система контроля версий, поэтому достаточно хорошо взаимодействует с различными ОС. GitHub специализированный веб-сервис с удобным интерфейсои, интегрирован с Git.
10. Faker для генерации тестовых данных. Эту библиотеку можно использовать для создания широкого спектра реально выглядящих данных от адресов до телефонных номеров.

**Перечень и описание возможных рисков при автоматизации**

1. Из-за отсутствия ТЗ и какой-либо документации по работе сервиса трудно понять, как он должен работать и какое поведение сервиса можно считать ошибкой.
2. Реальные данные могут отличаться от тестовых данных, в результате можно потерять отражение реальной статистики.
3. Отсутствие data_test_id усложняет написание и поддержку тестов.
4. Возможны сложности при настройке двух СУБД ("MySQL" и "PostgreSQL"), и корректном подключении к каждой из них.

**Интервальная оценка с учетом рисков (в часах)**

* Настройка ПО - 5

* Написание и отладка автотестов - 40

* Заведение issue по итогам прогонов автотестов - 8

* Подготовка отчета по итогам автоматизированного тестирования - 8

* Подготовка отчета по итогам автоматизации - 8

Итого с учетом рисков: 69 часов

**План сдачи работ**

* Планирование автоматизации тестирования: до 25.07.2021

* Автоматизация тестирования: до 30.07.2021

* Отчетные документы по итогам автоматизированного тестирования: до 1.08.2021

* Отчетные документы по итогам автоматизации: до 3.08.2021