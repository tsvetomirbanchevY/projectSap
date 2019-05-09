DROP DATABASE IF EXISTS project;
CREATE DATABASE project;
USE project;

create table users (
id int not null primary key auto_increment,
username varchar(255) not null,
password varchar(255) not null,
email varchar(255) not null,
firstname varchar(255) not null,
lastname varchar(255) not null,
bankaccount varchar(255) not null,
phone varchar(255) not null,
isvalid boolean not null default 0,
city varchar(255) not null
);

create table users_roles(
user_id int,
role_id int,
CONSTRAINT FOREIGN KEY(role_id)
REFERENCES roles(id),
CONSTRAINT FOREIGN KEY(user_id)
REFERENCES users(id)
);

create table roles(
id int not null primary key auto_increment,
typeuser ENUM('client', 'staff', 'boss') default 'client'
);

create table voucher (
id int not null primary key auto_increment,
textm varchar(255) not null,
user_id int,
user_staff_id int,
CONSTRAINT FOREIGN KEY(user_id)
REFERENCES users(id),
CONSTRAINT FOREIGN KEY(user_staff_id)
REFERENCES users(id)
);

create table cars (
id int not null primary key auto_increment,
brand varchar(255) not null,
model varchar(255) not null,
city varchar(255) not null,
isavailable boolean not null,
address varchar(255) not null
);

create table trips( 
id int not null primary key auto_increment,
dateoftrip DATETIME default now(),
startroute varchar(255) not null,
endroute varchar(255) default null,
price double not null,
car_id int,
user_id int,
CONSTRAINT FOREIGN KEY(user_id)
REFERENCES users(id),
CONSTRAINT FOREIGN KEY(car_id)
REFERENCES cars(id)
);

create table invoice(
id int not null primary key auto_increment,
typeinvoice ENUM('monthly', 'daily', 'overall'),
dateofinvoice DATETIME default now(),
totalcost double not null,
firm varchar(255) not null default 'spark',
user_id int,
CONSTRAINT FOREIGN KEY(user_id)
REFERENCES users(id)
);

create table staff_cars(
id int not null primary key auto_increment,
user_id int,
car_id int,
CONSTRAINT FOREIGN KEY(user_id)
REFERENCES users(id),
CONSTRAINT FOREIGN KEY(car_id)
REFERENCES cars(id),
car_main ENUM('repair', 'tire_check')
);

create table invoice_trips(
invoice_id int,
trip_id int,
CONSTRAINT FOREIGN KEY(invoice_id)
REFERENCES invoice(id),
CONSTRAINT FOREIGN KEY(trip_id)
REFERENCES trips(id)
);




