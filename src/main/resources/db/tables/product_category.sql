--liquibase formatted sql

--changeset AnushkaParajuli:product_category_0.0.1

CREATE TABLE product_category(
        id                          bigserial primary key,
        name                        varchar(255) not null,
        description                 varchar,
        status                      varchar
);
--rollback drop table if exists product_category