# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»

## Запуск тестов
1. склонировать репозиторий себе на ПК командой git clone
2. открыть проект в IDEA 
3. для запуска контейнеров с MySql запустить docker-compose.yml, использовав команду docker-compose up, далее в случае перезапуска приложения(контейнеров) или возникших проблем использовать docker-compose up -d --force-recreate
4. для получения отчета (Allure) использовать команды:
- ./gradlew allureReport – первый запуск для загрузки файлов allure
- ./gradlew allureServe – открывает отчёт в браузере
5. после окончания тестов завершить работу приложения, и остановить контейнеры командой docker-compose down