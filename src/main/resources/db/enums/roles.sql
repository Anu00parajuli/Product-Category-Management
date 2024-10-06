--liquibase formatted sql

--changeset AnushkaParajuli:roles_enum_0.0.1
CREATE TYPE roles AS ENUM('USER', 'ADMIN');

--rollback drop type roles if exists;