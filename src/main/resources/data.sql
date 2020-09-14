insert into roles (role_Name) values ('ROLE_ADMIN');
insert into roles (role_Name) values ('ROLE_USER');
insert into roles (role_Name) values ('ROLE_STAFF');
insert into customer(customer_id, enabled, encrypted_password, username) values (1, 1, '$2a$10$DBfEGVFeB21euN3QFFgGTuaKODWWP4HcNdptUpZZNiZI8r0fz5Vli', 'admin');
INSERT INTO cruise_ship (`cruise_id`, `deluxe_rooms_left`, `economy_rooms_left`, `luxury_rooms_left`, `name`, `staff_del_rooms_left`, `staff_eco_rooms_left`, `staff_lux_rooms_left`) VALUES ('1', '50', '50', '50', 'PROG', '20', '20', '10');
insert into customer_roles (customer_customer_id, roles_id) values (1, 1);

insert into customer(customer_id, enabled, encrypted_password, username) values (2, 1, '$2a$10$DBfEGVFeB21euN3QFFgGTuaKODWWP4HcNdptUpZZNiZI8r0fz5Vli', 'harnoor');
insert into customer_roles (customer_customer_id, roles_id) values (2, 3);