create table orders(
    id int AUTO_INCREMENT primary key,
    date datetime not null,
    customer_name varchar(80) not null,
    phone varchar(20) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zip_code varchar(9) not null,
    complement varchar(100),
    number integer,
    state char(2) not null,
    city varchar(100) not null,
    total_price double not null
);

create table order_ingredients(
    id int AUTO_INCREMENT primary key,
    ingredients_id int not null,
    order_id int not null,
    foreign key (ingredients_id) references ingredients(id),
    foreign key (order_id) references orders(id)
);

create table order_hamburgers(
    id int AUTO_INCREMENT primary key,
    order_id int not null,
    hamburgers_id int not null,
    foreign key (order_id) references orders(id),
    foreign key (hamburgers_id) references hamburgers(id)
);

create table order_drinks(
    id int AUTO_INCREMENT primary key,
    order_id int not null,
    drinks_id int not null,
    foreign key (order_id) references orders(id),
    foreign key (drinks_id) references drinks(id)
);

