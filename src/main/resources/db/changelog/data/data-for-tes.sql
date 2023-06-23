
insert into users(user_name, password, email) values
('Admin', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'admin@mail.com'),
('Manager', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'manager@mail.com');

insert into roles(role_name) values ('ROLE_MANAGER'), ('ROLE_ADMIN'), ('ROLE_EMPLOYEE');

insert into user_roles(user_id, role_id) values (1, 1), (1, 2), (2, 2);

insert into profiles(profile_name, balance, user_id)
values ('Family', 100000, 1), ('Personal', 200000, 1), ('Invest', 500000, 2);

insert into categories(title) values ('питание'), ('спорт'), ('отдых');

insert into transactions(description, amount, type, created, profile_id, category_id) values
('Купил продукты на неделю', 7829.5, 'EXPENSE',  now(), 1, 1),
('Билеты в отпуск', 35000, 'EXPENSE' ,  now(), 1, 3),
('Абонемент в спортзал', 15000, 'EXPENSE',  now(), 1, 2);


