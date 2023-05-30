
insert into users(user_name, password, email) values
('Admin', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'admin@mail.com'),
('Manager', '$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe', 'manager@mail.com');

insert into roles(role_name) values ('ROLE_MANAGER'), ('ROLE_ADMIN'), ('ROLE_EMPLOYEE');

insert into user_roles(user_id, role_id) values (1, 1), (1, 2), (2, 2);

