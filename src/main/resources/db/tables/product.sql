--liquibase formatted sql

--changeset AnushkaParajuli:product_0.0.1

CREATE TABLE product(
                        id                          UUID primary key DEFAULT gen_random_uuid(),
                        name                        varchar(255) not null,
                        description                 varchar,
                        price                       decimal(14, 2),
                        status                      status not null,
                        created_at                  timestamp default now(),
                        product_category_id         UUID references product_category(id)
);
--rollback drop table if exists product