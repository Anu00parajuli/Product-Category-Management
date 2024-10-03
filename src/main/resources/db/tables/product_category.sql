--liquibase formatted sql

--changeset AnushkaParajuli:product_category_0.0.1

CREATE TABLE product_category(
        id                          UUID primary key DEFAULT gen_random_uuid(),
        name                        varchar(255) not null,
        description                 varchar,
        status                      status not null
);
--rollback drop table if exists product_category