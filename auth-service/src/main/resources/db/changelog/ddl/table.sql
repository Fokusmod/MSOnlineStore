--liquibase formatted sql

--changeset fokusmod:create_tables


create table users
(
    id         bigserial primary key,
    username   varchar(50)  not null unique,
    password   varchar(100) not null,
    email      varchar(150) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         serial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);
create table privileges
(
    id   serial primary key,
    name varchar(50)
);

create table privileges_for_roles
(
    role_id      int not null,
    privilege_id int not null,
    primary key (role_id, privilege_id),
    foreign key (role_id) references roles (id),
    foreign key (privilege_id) references privileges (id)
);

