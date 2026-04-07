# AQA Java Project

Автотесты для SauceDemo с использованием Java 21, JUnit 5, Selenide, Allure, WireMock, Docker Compose.

## Запуск тестов

mvn clean test

## Генерация Allure-отчёта

mvn allure:report
allure open allure-report

## Параллельный запуск

Тесты запускаются параллельно в 4 потока.

## CI/CD

GitHub Actions запускает тесты на каждый push в ветку main.
Allure-отчёт автоматически публикуется на GitHub Pages.

## Структура проекта

src/test/java/
├── config/
│   └── UiTestConfig.java
└── tests/
├── LoginTest.java
└── E2eTest.java

## Allure-отчёт

После успешного прогона CI:
https://Andrewww555.github.io/AQA_Java_Project/allure-report/

## Технологии

Selenide - UI-тестирование
Rest Assured - API-тестирование
Allure - отчёты
WireMock - мокирование API
JUnit 5 - тестовый фреймворк
Docker Compose - запуск WireMock в CI
GitHub Actions - CI/CD
GitHub Pages - хостинг отчётов