create table users (
    id int AUTO_INCREMENT primary key,
    email varchar(60) not null unique,
    password varchar(255) not null
);