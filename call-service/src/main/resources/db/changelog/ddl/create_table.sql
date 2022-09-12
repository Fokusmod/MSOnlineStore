--liquibase formatted sql

--changeset fokusmod:create_request_tables
create table call_requests
(
    id      bigserial primary key,
    user_id bigserial not null unique
);


create table cart_items
(
    id    bigserial primary key,
    title varchar(255) not null ,
    count int not null ,
    price int not null ,
    sum   int not null,
    request_id bigserial references call_requests(id)
);



