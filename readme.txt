Чтобы создать бд в докер контейнере введите в комендной строке (у кого линукс и докер в руте надо перед командами писать sudo):
docker run --name postgres_finance -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=finance_db -p 5433:5432 -d postgres:latest

посмотреть создался ли контейнер с именем postgres_finance можно командой (вывод существующих контейнеров)
docker ps -a

Для проверки создалась ли база данных смотрим в постгрес из терминала
docker exec -it postgres_finance psql -U postgres

подключились к бд
\c finance_db;

выход
exit

что бы в дальнейшем запустить контейнер с бд надо будет набирать
docker start postgres_finance

Если что то испортили в бд можно легко удалить контейнер
docker rm postgres_finance

и после этого снова запустить команду создания контейнера с бд