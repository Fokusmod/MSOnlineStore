--liquibase formatted sql

--changeset fokusmod:insert_data

insert into categories (title)
values ('Products'),
       ('Beverages'),
       ('Vegetables'),
       ('Fruit');

insert into products (title, price, category_id)
values ('Apple', 150, 4),
       ('Banana', 100, 4),
       ('Orange', 120, 4),
       ('Papaya', 120, 4),
       ('Milk', 140, 2),
       ('Cheese', 270, 1),
       ('Cheeps', 100, 1),
       ('Potato', 70, 3),
       ('Chicken egg', 120, 1),
       ('Meat', 300, 1),
       ('Chocolate', 130, 1),
       ('Kiwi', 140, 4),
       ('Tomato', 120, 3),
       ('Pineapple', 200, 4),
       ('Bread', 300, 1),
       ('Tea', 250, 1),
       ('Coffee', 300, 1),
       ('Cacao', 150, 1),
       ('Bear', 210, 2),
       ('Fish', 300, 1);



