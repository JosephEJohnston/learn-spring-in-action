create table if not exists Taco_Order (
  id identity,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
);

create table if not exists Ingredient_Ref (
    id identity,
  `ingredient` varchar(4) not null,
  taco bigint not null,
  taco_key bigint not null
);


create table if not exists Ingredient (
  `id` varchar(4) primary key,
  `name` varchar(25) not null,
  `type` varchar(10) not null
);

alter table IF EXISTS Taco
    add foreign key (taco_order) references Taco_Order(id);
alter table IF EXISTS Ingredient_Ref
    add foreign key (`ingredient`) references Ingredient(`id`);

create table if not exists `User_` (
    `id` identity,
    `username` varchar(24),
    `fullname` varchar(64),
    `street` varchar(32),
    `city` varchar(32),
    `state` varchar(32),
    `zip` varchar(32),
    `phone_number` varchar(64)
);