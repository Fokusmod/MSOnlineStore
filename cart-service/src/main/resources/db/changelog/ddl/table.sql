--liquibase formatted sql

--changeset fokusmod:create_order tables

create table statuses
(
    id    bigserial primary key,
    title varchar(255) not null
);

insert into statuses (title)
values ('Оплачено'),
       ('Не оплачено');

create table orders
(
    id        bigserial primary key,
    username  varchar(255) not null,
    address   varchar(255) not null,
    phone     varchar(255) not null,
    status_id bigint references statuses (id)
);

create table order_items
(
    id        bigserial primary key,
    title    varchar(255) not null,
    count    int4         not null,
    price    decimal      not null,
    sum      decimal      not null,
    order_id bigint references orders (id)
);




