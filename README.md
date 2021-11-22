# RecipesAlderaSoft
### Test task AlderaSoft company

### Тестовое задание Android Разработчик

Реализовать простое приложение,
 загружающее и выводящее список рецептов с возможностью просмотра деталей по каждому рецепту.

### Время выполнения: 10,5 часов 

![image](https://user-images.githubusercontent.com/69672210/142805228-2e425d19-73a2-4803-adf9-5847dd88cf4a.png)
![image](https://user-images.githubusercontent.com/69672210/142805286-1df95799-4f34-4fe7-b556-cfbd12125d4f.png)

### Функциональные требования

1. Главный экран: Спискок рецептов,
 загружаемый с серверного API в формате JSON. Каждый элемент списка должен содержать:

- фотографию рецепта;

- название рецепта;

- краткое описание (должно
 обрезаться до двух строк).

2. Детали рецепта: по
 нажатию на ячейку должен открываться экран деталей рецепта, который содержит:

- фотографию рецепта

- название рецепта;

- краткое описание;

- инструкции по приготовлению;

- уровень сложности:
 от 1 до 5 

- отображение похожих рецептов

- переход к деталям похожих рецептов 

Общие технические требования:
1) Использование языка Kotlin
2) Реализовать приложение на архитектуре MVVM или MVI
3) Для серверного взаимодействия использовать библиотеку Retrofit2
4) Для отображения фотографий можно использовать любую библиотеку
5) минимальная api версия Android - 21
6) Выводить TOAST-сообщение или любое другое уведомление, для отображения ошибок.
7) Для работы с асинхронностью использовать RxJava2 или (желательно) Kotlin Coroutines и Kotlin Flow
8) Желательно использовать библиотеку для внедрения зависимостей
9) Желательно использовать архитектурные компоненты Android

API Спецификация ниже.

Base URL:

https://test.kode-t.ru/

Пример запроса: baseurl/recipes

1. Список рецептов.

GET /recipes

2. Детали рецепта.

GET /recipes/:uuid

Выполненное задание необходимо разместить в GitHub, GitLab или любом другом удобном.
