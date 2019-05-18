insert into users (username, password, email, firstname, lastname, bankaccount, phone, isvalid, city, license)
values('az', '$2a$04$7GojSf8/kitGfcYqXAMSEuO1UzLmi2JPAOWczU.jyW9LM7xawepa2', 'az', 'az', 'az', 'az', '088', 1, 'sliven', 'C:\Users\LENOVO\Downloads\1.jpg'),
('ti', '$2a$04$7GojSf8/kitGfcYqXAMSEuO1UzLmi2JPAOWczU.jyW9LM7xawepa2', 'ti', 'ti', 'ti', 'ti', '078', 1, 'sofia', 'C:\Users\LENOVO\Downloads\1.jpg'),
('te', 'te', 'te', 'te', 'te', 'te', '087', 1, 'varna','C:\Users\LENOVO\Downloads\1.jpg');

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
values('v1', 1, 3);

 insert into cars (brand, model, isavailable, address, regplate)
 values("audi", "a4", 1, "sliven drujba", "ch4400"),
 ("audi", "a4", 0, "sliven kamani", "ch3030");

insert into trips (dateoftrip, startroute, endroute, price, car_id, user_id)
values('2000-02-02', 'sofia','sliven', 100.00, 1, 1),
('2000-02-02', 'sofia','sliven', 100.00, 2, 2);

insert into invoice (typeinvoice, dateofinvoice, totalcost, user_id)
values('daily', now(), 100.00, 1);

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
-- select * from staff_cars;
-- select * from invoice_trips; 





