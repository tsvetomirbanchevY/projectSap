insert into users (username, password, email, firstname, lastname, bankaccount, phone, isvalid, city, license)
values('ivan555', '$2a$04$7GojSf8/kitGfcYqXAMSEuO1UzLmi2JPAOWczU.jyW9LM7xawepa2', 'ivan555@gmail.com', 'ivan', 'ivanov', 'ivanov', '0888888877', 1, 'sliven', '12555555'),
('ti7898', '$2a$04$7GojSf8/kitGfcYqXAMSEuO1UzLmi2JPAOWczU.jyW9LM7xawepa2', 'ivanaivanova@abv.bg', 'ivana', 'petrova', 'petrova', '089888877', 1, 'sofia', '200000'),
('tetete', '$2a$04$7GojSf8/kitGfcYqXAMSEuO1UzLmi2JPAOWczU.jyW9LM7xawepa2', 'petarpekov@gmail.com', 'petar', 'pekov', 'petkov', '0878888877', 1, 'varna','3000000');

insert into roles (typeuser)
values('client'),
('boss'),
('staff');

insert into users_roles (user_id, role_id)
values(2,2),
(3,3);

insert into users_roles (user_id)
values(1);

insert into voucher (textm, user_id, user_staff_id)
values('voucher for a free trip', 1, 3);

 insert into cars (brand, model, isavailable, address, regplate)
 values("audi", "a4", 1, "sliven drujba", "ch4400"),
 ("audi", "a4", 0, "sliven kamani", "ch3030");

insert into trips (dateoftrip, startroute, endroute, price, car_id, user_id)
values(now(), 'sofia','sliven', 10.00, 1, 1),
(now(), 'sofia','sliven', 10.00, 2, 2);

insert into invoice (dateofinvoice, totalcost, user_id)
values(now(), 10.00, 1);

 insert into staff_cars (car_id, user_id, car_main)
 values(1, 1, 'repair');
 
 insert into invoice_trips
 values(1, 1);
 
select * from users;
select * from roles;
select * from users_roles;
select * from voucher;
select * from cars;
select * from trips;
select * from staff_cars;
select * from invoice_trips; 
select * from invoice; 





