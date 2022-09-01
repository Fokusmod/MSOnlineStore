--liquibase formatted sql

--changeset fokusmod:create_product_tables
create table categories
(
    id    bigserial primary key,
    title varchar(255)
);


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)

);


