-- insert into Category (id, name, parent_id) values (1, 'Elektronika', NULL );
-- insert into Category (id, name, parent_id) values (2, 'Moda', NULL );
-- insert into Category (id, name, parent_id) values (3, 'Dom', NULL );
-- insert into Category (id, name, parent_id) values (4, 'Sport i wypoczynek', NULL );
-- insert into Category (id, name, parent_id) values (5, 'Motoryzacja', NULL );
-- insert into Category (id, name, parent_id) values (6, 'Kolekcje i sztuka', NULL );
-- insert into Category (id, name, parent_id) values (7, 'Kultura i rozrywka', NULL );
-- insert into Category (id, name, parent_id) values (8, 'Komputery', 1 );
-- insert into Category (id, name, parent_id) values (9, 'Telewizory', 1 );
-- insert into Category (id, name, parent_id) values (10, 'Stacjonarne', 8 );
-- insert into Category (id, name, parent_id) values (11, 'Laptopy', 8 );
--
-- insert into User (id, username, email) VALUES (1, 'Abacki', 'portalaukcyjnyai@gmail.com');
-- insert into User (id, username, email) values (2, 'Kowalski', 'portalaukcyjnyai@gmail.com');
-- insert into User (id, username, email) values (3, 'Cabacka', 'portalaukcyjnyai@gmail.com');
--
-- insert into auction (id, description, end_date_time, is_active, photo_url, thumbnail_url, start_date_time, starting_price, title, category_id, owner_id)
-- values (1, 'Opis aukcji 1', '2018-01-01T10:15:20', true, 'auctionPhotos/default.png', 'auctionPhotos/default_thumbnail.png', '2017-12-10T08:12:36', 30, 'Aukcja 1', 10, 1);
-- insert into auction (id, description, end_date_time, is_active, photo_url, thumbnail_url, start_date_time, starting_price, title, category_id, owner_id)
-- values (2, 'Opis aukcji 2', '2018-02-05T10:15:20', true, 'auctionPhotos/default.png', 'auctionPhotos/default_thumbnail.png','2017-11-25T20:20:20', 400, 'Aukcja 2', 11, 2);


insert into app_role (id, role_name) values (2, 'ROLE_STANDARD_USER');
insert into app_role (id, role_name) values (1, 'ROLE_ADMIN');

insert into Category (name, parent_id) values ('Elektronika', NULL );
insert into Category (name, parent_id) values ('Moda', NULL );
insert into Category (name, parent_id) values ('Dom', NULL );
insert into Category (name, parent_id) values ('Sport i wypoczynek', NULL );
insert into Category (name, parent_id) values ('Motoryzacja', NULL );
insert into Category (name, parent_id) values ('Kolekcje i sztuka', NULL );
insert into Category (name, parent_id) values ('Kultura i rozrywka', NULL );
insert into Category (name, parent_id) values ('Komputery', 1 );
insert into Category (name, parent_id) values ('Telewizory', 1 );
insert into Category (name, parent_id) values ('Stacjonarne', 8 );
insert into Category (name, parent_id) values ('Laptopy', 8 );

insert into APP_USER (ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME) VALUES
  (1, 'admin@admin', 'admin', 'admin', '$2a$10$RTYKjnZD8yFH48wjWY1MHORKFOAPKIuQpJ1J9YRZBSO6y/O6JLgwS', 'admin');
insert into APP_USER (ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME) VALUES
  (2, 'jan@kowalski.pl', 'Jan', 'Kowalski', '$2a$10$ZfcpLZhOJ7Lr7ig9PM3lceLFmlEzu66zOeoRhRlzIpBEuItaBsQjW', 'kowalski');
insert into APP_USER (ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME) VALUES
  (3, 'anna.abacka@gmail.com', 'Anna', 'Abacka', '$2a$10$a5TOGEaI.TXYDOix5cd5E.RaEjk1H9W6s0ZaQ2bK3/Fn6AJ89k1A2', 'abacka');

INSERT INTO USER_ROLE VALUES (1,1);
INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (1, 2);
INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (2, 2);
INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (3, 2);

--
-- insert into auction (description, end_date_time, is_active, photo_url, thumbnail_url, start_date_time, starting_price, title, category_id, owner_id)
-- values ('Opis aukcji 1', '2018-01-01T10:15:20', 1, 'auctionPhotos/default.png', 'auctionPhotos/default_thumbnail.png', '2017-12-10T08:12:36', 30, 'Aukcja 1', 10, 1);
-- insert into auction (description, end_date_time, is_active, photo_url, thumbnail_url, start_date_time, starting_price, title, category_id, owner_id)
-- values ('Opis aukcji 2', '2018-02-05T10:15:20', 1, 'auctionPhotos/default.png', 'auctionPhotos/default_thumbnail.png','2017-11-25T20:20:20', 400, 'Aukcja 2', 11, 2);
--
-- insert into auction_offer (amount, submission_date, auction_id, user_id) values (35, '2017-12-15T08:12:36', 1, 2);
-- insert into auction_offer (amount, submission_date, auction_id, user_id) values (500, '2017-12-01T08:12:36', 2, 1);
-- insert into auction_offer (amount, submission_date, auction_id, user_id) values (45, '2017-12-18T08:12:36', 1, 3);
-- insert into auction_offer (amount, submission_date, auction_id, user_id) values (600, '2017-12-05T08:12:36', 2, 3);