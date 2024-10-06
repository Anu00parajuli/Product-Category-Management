--liquibase formatted sql

--changeset AnushkaParajuli:roles_relation_0.0.1

CREATE TABLE roles(

         id SERIAL PRIMARY KEY,
         name VARCHAR(50) UNIQUE NOT NULL


);

--rollback drop table if exists roles;