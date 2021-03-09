

-- Contraseña: Admin1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (1, 'Admin admin', 'admin@openwebinars.net','admin','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
    
insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');


-- Contraseña: Marialopez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (2, 'María López', 'maria.lopez@openwebinars.net','marialopez','$2a$10$ev.rv6yUA.UE9.Ndw4aSC.wRo6UlP6OkjAe48SmEN.elw4WAyfT0S','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (3, 'Ángel Martínez', 'angel.martinez@openwebinars.net','angelmartinez','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,false);

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- Contraseña: Anajimenez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (4, 'Ana Jiménez', 'ana.jimenez@openwebinars.net','anajimenez','$2a$10$IF4e6GpTAO5pQOLwy.Bn7.hBGgeOOMCIyEhvEkeikkrlBY5emp6vy','https://api.adorable.io/avatars/285/ana.jimenez@openwebinars.net.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (5, 'rodrigo ramirez', 'rram@openwebinars.net','rramirez','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (5,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (6, 'Carlos Villagran', 'carlitos@openwebinars.net','cvilla','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (6,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (7, 'Gabriel Perez', 'gper@openwebinars.net','gabper','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (7,'USER');
insert into user_entity_roles (user_entity_id, roles) values (7,'ADMIN');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (9, 'Carlos Lopez', 'carlopez@openwebinars.net','clop','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (9,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (8, 'Sebastian Sangermano', 'seba.sanger88@gmail.com','seba.sanger88@gmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (8,'USER');
insert into user_entity_roles (user_entity_id, roles) values (8,'ADMIN');


insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (10, 'Gabriel Sangermano', 'seba@hotmail.com','seba@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (10,'USER');


