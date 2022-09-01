--liquibase formatted sql

--changeset fokusmod:insert_data

insert into roles (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_GUEST');

insert into users (username, password, email)
values ('admin', '$2a$12$lHRYfWz9V0YNefV0iH/2he7bbljUqw9uDgbDIIlzheNvgTbcnPSaK', 'admin@mail.com'),
       ('user', '$2a$12$Y8YFr5L3jmQRK7qjnbuTfOrTraiwHlbO3BFAvgpn0X816hDloCE.2', 'user@mail.com'),
       ('guest', '$2a$12$/IIkzoPiFpBI4IZmOX5a3ey4wuU1t.nSHo/Ix9NLsksNmP8J7CvQy', 'guest@mail.com');

insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

insert into privileges (name)
values ('read'),
       ('write'),
       ('update'),
       ('delete');

insert into privileges_for_roles (role_id, privilege_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (3, 1);

