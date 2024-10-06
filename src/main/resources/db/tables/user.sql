--liquibase formatted sql

--changeset AnushkaParajuli:user_0.0.1

CREATE TABLE users(
    user_id             serial primary key ,
    username            varchar unique not null ,
    password            varchar not null,
    role                roles not null
);

--rollback drop table if exists user;