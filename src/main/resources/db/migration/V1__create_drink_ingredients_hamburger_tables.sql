create table drinks(
    id int AUTO_INCREMENT primary key,
    name varchar(30) not null,
    description varchar(100) DEFAULT null,
    price double not null,
    flag_sugar ENUM('WITHOUT_SUGAR', 'WITH_SUGAR') not null
);

create table ingredients(
    id int AUTO_INCREMENT primary key,
    name varchar(30) not null,
    description varchar(100) DEFAULT null,
    price double not null,
    flag_additional ENUM('ADDITIONAL', 'NOT_ADDITIONAL') not null
);

create table hamburgers(
    id int AUTO_INCREMENT primary key,
    name varchar(30) not null,
    description varchar(100),
    price double not null
);

create table hamburgers_ingredients(
    id int AUTO_INCREMENT primary key,
    ingredients_id int not null,
    hamburgers_id int not null,
    foreign key (ingredients_id) references ingredients(id),
    foreign key (hamburgers_id) references hamburgers(id)
);
