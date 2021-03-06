# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»

## Запуск тестов

1. склонировать репозиторий себе на ПК командой `git clone`
2. открыть проект в IDEA
3. для запуска контейнеров с MySql запустить docker-compose.yml, использовав команду `docker-compose up`, далее в случае
   перезапуска приложения(контейнеров) или возникших проблем использовать `docker-compose up -d --force-recreate`
4. для запуска приложения:

- под MySQL использовать команду
  `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts.\aqa-shop.jar`

5. запуск тестов:

- для запуска под MySQL использовать команду
  `./gradlew test --info`

6. для получения отчета (Allure) использовать команды:

- `./gradlew allureReport` – первый запуск для загрузки файлов allure
- `./gradlew allureServe` – открывает отчёт в браузере

7. после окончания тестов завершить работу приложения, и остановить контейнеры командой `docker-compose down`

## Документация
1. [План автоматизации тестирования](https://github.com/TanyaDRO/Course_project/blob/master/docs/Plan.md)
2. [Отчёт по итогам автоматизированного тестирования](https://github.com/TanyaDRO/Course_project/blob/master/docs/Report.md)
3. [Отчёт по итогам автоматизации](https://github.com/TanyaDRO/Course_project/blob/master/docs/Summary.md)
