

-- Contraseña: Admin1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (1, 'Admin admin', 'admin@hotmail.com','admin','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
    
insert into user_entity_roles (user_entity_id, roles) values (1,'USER');
insert into user_entity_roles (user_entity_id, roles) values (1,'ADMIN');


-- Contraseña: Marialopez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (2, 'María López', 'maria.lopez@hotmail.com','marialopez','$2a$10$ev.rv6yUA.UE9.Ndw4aSC.wRo6UlP6OkjAe48SmEN.elw4WAyfT0S','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (2,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (3, 'Ángel Martínez', 'angel.martinez@hotmail.com','angelmartinez','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,false);

insert into user_entity_roles (user_entity_id, roles) values (3,'USER');

-- Contraseña: Anajimenez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (4, 'Ana Jiménez', 'ana.jimenez@hotmail.com','anajimenez','$2a$10$IF4e6GpTAO5pQOLwy.Bn7.hBGgeOOMCIyEhvEkeikkrlBY5emp6vy','https://api.adorable.io/avatars/285/ana.jimenez@hotmail.com.png',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (4,'USER');
-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (5, 'rodrigo ramirez', 'rram@hotmail.com','rramirez','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (5,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (6, 'Carlos Villagran', 'carlitos@hotmail.com','cvilla','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (6,'USER');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (7, 'Gabriel Perez', 'gper@hotmail.com','gabper','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (7,'USER');
insert into user_entity_roles (user_entity_id, roles) values (7,'ADMIN');

-- Contraseña: Angelmartinez1
insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (9, 'Carlos Lopez', 'carlopez@hotmail.com','clop','$2a$10$9joAo0/q0z2vYgdKUYQ7kuahy7xRBRZF9GNkmOsd6hbCvqFmH6Ueu','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (9,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (8, 'Sebastian Sangermano', 'seba.sanger88@gmail.com','seba.sanger88@gmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);

insert into user_entity_roles (user_entity_id, roles) values (8,'USER');
insert into user_entity_roles (user_entity_id, roles) values (8,'ADMIN');


insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (10, 'Gabriel Sangermano', 'seba@hotmail.com','seba@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (10,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (11, 'Ricardo Marin', 'riki@hotmail.com','riki@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (11,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (12, 'Oscar Lopez', 'oscar@hotmail.com','oscar@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (12,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (13, 'Pedro Rodriguez', 'pedro@hotmail.com','pedro@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (13,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (14, 'Alberto Caseres', 'albert@hotmail.com','albert@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (14,'USER');

insert into users (id, full_name, email, username, password, avatar, created_at, last_password_change_at,enabled) 
values (15, 'Manuel kito', 'manukit@hotmail.com','manukit@hotmail.com','$2a$10$DBJhFdEGTeAqoLLsGfXwYObYXpt/amU0wpsRtKQtwJdC5n.MOXgxC','',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,true);
      
insert into user_entity_roles (user_entity_id, roles) values (15,'USER');