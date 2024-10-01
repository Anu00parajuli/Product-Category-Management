--liquibase formatted sql

--changeset AnushkaParajuli:product_0.0.1

CREATE TABLE product(
                        id                          bigserial primary key,
                        name                        varchar(255) not null,
                        description                 varchar,
                        price                       decimal,
                        status                      varchar,
                        created_at                  timestamp default now()
);
--rollback drop table if exists product