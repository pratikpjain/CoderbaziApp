drop table if exists users cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;

create table users (
    name varchar(255) not null,
    user_name varchar(255) not null,
    phone_number varchar(10) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_name)
);