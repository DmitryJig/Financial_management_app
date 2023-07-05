# Приложение для управления финансами

Добро пожаловать в приложение для управления финансами! Это удобный инструмент – веб-приложение, разработанное для помощи в управлении личными финансами, финансами семьи или финансами рабочего коллектива. Наше приложение поможет вам эффективно контролировать ваши финансы, управлять доходами и расходами, анализировать финансовые операции и многое другое.

## Особенности приложения:

- **Управление финансовыми операциями**: С помощью этого приложения вы сможете легко создавать, изменять и удалять финансовые операции, такие как доходы и расходы. Вы сможете быстро вносить информацию о своих финансовых транзакциях и иметь полный контроль над своими финансами.

- **Отображение баланса и истории операций**: Вы всегда будете в курсе своего текущего баланса и истории всех финансовых операций. Приложение позволяет вам наглядно отслеживать изменения вашего бюджета и анализировать, какие операции оказывают наибольшее влияние на ваше финансовое положение.

- **Группировка и анализ данных**: Приложение предлагает удобный способ группировать финансовые операции по категориям, источникам доходов и расходов. Это позволяет вам проводить анализ и получать детальные отчеты о том, как и куда уходят ваши деньги. Вы сможете легко увидеть, на что вы тратите больше всего и где можно сократить расходы.

- **Статистика и анализ трендов**: Приложение предоставляет возможность собрать статистическую информацию и проанализировать тренды в ваших финансовых операциях. Вы сможете увидеть изменения в вашем балансе, доходах и расходах. Это поможет вам лучше понять свои финансовые привычки и принимать осознанные решения о своих финансах.

## Ссылка на страницу приложения онлайн:
**[Приложение для управления финансами](http://62.113.104.3:8089/finance-app-dev/)**

## Запуск приложения локально с компьютера:
**Требования:**
- Intelij IDEA или другое приложение для компиляции и запуска
- Java версии не ниже 11
- Doсker
- Web-браузер

**Запуск:**
- **Запустить докер**.
- Выполнить на нём, чтобы создать БД в докер контейнере, в комендной строке (в случае линукс и докер в руте надо перед командами писать sudo):
```sh
  docker run --name postgres_finance -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=finance_db -p 5433:5432 -d postgres:latest
```
- посмотреть создался ли контейнер с именем postgres_finance можно командой (вывод существующих контейнеров)
```sh
  docker ps -a
```
- Для проверки создалась ли база данных смотрим в постгрес из терминала
```sh
  docker exec -it postgres_finance psql -U postgres
```
- Подключение к БД
```sh
  \c finance_db
```
  Для выхода используем
```sh
  exit
```
- **(Для повторного запуска БД используем)**
```sh
  docker start postgres_finance
```
- **Скачать файлы программы с ГитХаб:**

  **[Страница проекта на ГитХаб](https://github.com/DmitryJig/Financial_management_app)**

- **Удалить из папки src/main/resources/ файл application-prod.yaml**

- **Открыть скачанную папку в среде разработки**

- **Запустить приложение из среды разработки(Файл запуска AppApplication.java)**

- **Перейти в браузере на страницу:**

  **[Локальная страница приложения на вашем компьютере](http://localhost:8189/app/index.html)**

- **Пользоваться приложением(см. пункт "Работа с приложением")**

## Работа с приложением:

1. **Зарегистрируйтесь или авторизуйтесь** создайте свою учетную запись или войдите под уже существующей.
2. **Создайте или выберите имеющийся профиль** в приложении, управляйте им.
3. **Добавьте свои финансовые операции**, указывая тип (доход или расход), сумму, категорию и дату операции.
4. **Отслеживайте свой баланс и историю операций** в соответствующем разделе.
5. **Группируйте операции по категориям и источникам** для получения детальных отчетов и анализа доходов и расходов.
6. **Исследуйте движение своих денежных средств**, чтобы лучше понять свои финансовые привычки и принимать осознанные финансовые решения.

Управляйте своими финансами с нашим приложением! Если у вас возникли вопросы или вам нужна помощь, обратитесь к справочной документации или свяжитесь с нами.

## Скриншоты:

Страница регистрации пользователя:

![Скриншот1](https://github.com/DmitryJig/Financial_management_app/blob/master/164017.jpg)

Страница управления профилями:

![Скриншот2](https://github.com/DmitryJig/Financial_management_app/blob/master/264056.jpg)

Страница управления транзакциями:

![Скриншот3](https://github.com/DmitryJig/Financial_management_app/blob/master/363747.jpg)
