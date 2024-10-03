--liquibase formatted sql

--changeset AnushkaParajuli:status_enum_0.0.1
CREATE TYPE status AS ENUM('ACTIVE', 'INACTIVE');

--rollback drop type status if exists;