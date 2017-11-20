

CREATE DATABASE sakila;

CREATE TABLE actor(
actor_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (actor_id),
UNIQUE KEY (actor_id)
);

CREATE TABLE address(
address_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
address VARCHAR(50) NOT NULL,
address2 VARCHAR(50) NULL,
district VARCHAR(20) NOT NULL,
city_id SMALLINT UNSIGNED(5) NOT NULL,
postal_code VARCHAR(10) NULL,
phone VARCHAR(20) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (address_id),
UNIQUE KEY (address_id),
CONSTRAINT fk_city_id_city FOREIGN KEY (city_id) REFERENCES city(city_id)
);

CREATE TABLE category(
category_id TINYINT UNSIGNED(3) NOT NULL AUTO_INCREMENT,
name VARCHAR(25) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (category_id),
UNIQUE KEY (category_id)
);

CREATE TABLE city(
city_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
city VARCHAR(50) NOT NULL,
country_id SMALLINT UNSIGNED(5) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (city_id),
UNIQUE KEY (city_id),
CONSTRAINT fk_country_id_country FOREIGN KEY (country_id) REFERENCES country(country_id)
);

CREATE TABLE country(
country_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
country VARCHAR(50) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (country_id),
UNIQUE KEY (country_id)
);

CREATE TABLE customer(
customer_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
store_id TINYINT UNSIGNED(3) NOT NULL,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
email VARCHAR(50) NULL,
address_id SMALLINT UNSIGNED(5) NOT NULL,
active BIT(0) NOT NULL,
create_date DATETIME(19) NOT NULL,
last_update TIMESTAMP(19) NULL,
PRIMARY KEY (customer_id),
UNIQUE KEY (customer_id),
CONSTRAINT fk_address_id_address FOREIGN KEY (address_id) REFERENCES address(address_id), 
CONSTRAINT fk_store_id_store FOREIGN KEY (store_id) REFERENCES store(store_id)
);

CREATE TABLE film(
film_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
title VARCHAR(255) NOT NULL,
description TEXT(65535) NULL,
release_year YEAR(0) NULL,
language_id TINYINT UNSIGNED(3) NOT NULL,
original_language_id TINYINT UNSIGNED(3) NULL,
rental_duration TINYINT UNSIGNED(3) NOT NULL,
rental_rate DECIMAL(4, 2) NOT NULL,
length SMALLINT UNSIGNED(5) NULL,
replacement_cost DECIMAL(5, 2) NOT NULL,
rating ENUM(5) NULL,
special_features SET(54) NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (film_id),
UNIQUE KEY (film_id),
CONSTRAINT fk_language_id_language FOREIGN KEY (language_id) REFERENCES language(language_id), 
CONSTRAINT fk_original_language_id_language FOREIGN KEY (original_language_id) REFERENCES language(language_id)
);

CREATE TABLE film_actor(
actor_id SMALLINT UNSIGNED(5) NOT NULL,
film_id SMALLINT UNSIGNED(5) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (actor_id, film_id),
UNIQUE KEY (actor_id, film_id),
CONSTRAINT fk_actor_id_actor FOREIGN KEY (actor_id) REFERENCES actor(actor_id), 
CONSTRAINT fk_film_id_film FOREIGN KEY (film_id) REFERENCES film(film_id)
);

CREATE TABLE film_category(
film_id SMALLINT UNSIGNED(5) NOT NULL,
category_id TINYINT UNSIGNED(3) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (category_id, film_id),
UNIQUE KEY (film_id, category_id),
CONSTRAINT fk_category_id_category FOREIGN KEY (category_id) REFERENCES category(category_id), 
CONSTRAINT fk_film_id_film FOREIGN KEY (film_id) REFERENCES film(film_id)
);

CREATE TABLE film_text(
film_id SMALLINT(5) NOT NULL,
title VARCHAR(255) NOT NULL,
description TEXT(65535) NULL,
PRIMARY KEY (film_id),
UNIQUE KEY (film_id)
);

CREATE TABLE inventory(
inventory_id MEDIUMINT UNSIGNED(8) NOT NULL AUTO_INCREMENT,
film_id SMALLINT UNSIGNED(5) NOT NULL,
store_id TINYINT UNSIGNED(3) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (inventory_id),
UNIQUE KEY (inventory_id),
CONSTRAINT fk_film_id_film FOREIGN KEY (film_id) REFERENCES film(film_id), 
CONSTRAINT fk_store_id_store FOREIGN KEY (store_id) REFERENCES store(store_id)
);

CREATE TABLE language(
language_id TINYINT UNSIGNED(3) NOT NULL AUTO_INCREMENT,
name CHAR(20) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (language_id),
UNIQUE KEY (language_id)
);

CREATE TABLE payment(
payment_id SMALLINT UNSIGNED(5) NOT NULL AUTO_INCREMENT,
customer_id SMALLINT UNSIGNED(5) NOT NULL,
staff_id TINYINT UNSIGNED(3) NOT NULL,
rental_id INT(10) NULL,
amount DECIMAL(5, 2) NOT NULL,
payment_date DATETIME(19) NOT NULL,
last_update TIMESTAMP(19) NULL,
PRIMARY KEY (payment_id),
UNIQUE KEY (payment_id),
CONSTRAINT fk_customer_id_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id), 
CONSTRAINT fk_rental_id_rental FOREIGN KEY (rental_id) REFERENCES rental(rental_id), 
CONSTRAINT fk_staff_id_staff FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

CREATE TABLE rental(
rental_id INT(10) NOT NULL AUTO_INCREMENT,
rental_date DATETIME(19) NOT NULL,
inventory_id MEDIUMINT UNSIGNED(8) NOT NULL,
customer_id SMALLINT UNSIGNED(5) NOT NULL,
return_date DATETIME(19) NULL,
staff_id TINYINT UNSIGNED(3) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (rental_id),
UNIQUE KEY (rental_id, rental_date, inventory_id, customer_id),
CONSTRAINT fk_customer_id_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id), 
CONSTRAINT fk_inventory_id_inventory FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id), 
CONSTRAINT fk_staff_id_staff FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

CREATE TABLE staff(
staff_id TINYINT UNSIGNED(3) NOT NULL AUTO_INCREMENT,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
address_id SMALLINT UNSIGNED(5) NOT NULL,
picture MEDIUMBLOB(16777215) NULL,
email VARCHAR(50) NULL,
store_id TINYINT UNSIGNED(3) NOT NULL,
active BIT(0) NOT NULL,
username VARCHAR(16) NOT NULL,
password VARCHAR(40) NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (staff_id),
UNIQUE KEY (staff_id),
CONSTRAINT fk_address_id_address FOREIGN KEY (address_id) REFERENCES address(address_id), 
CONSTRAINT fk_store_id_store FOREIGN KEY (store_id) REFERENCES store(store_id)
);

CREATE TABLE store(
store_id TINYINT UNSIGNED(3) NOT NULL AUTO_INCREMENT,
manager_staff_id TINYINT UNSIGNED(3) NOT NULL,
address_id SMALLINT UNSIGNED(5) NOT NULL,
last_update TIMESTAMP(19) NOT NULL,
PRIMARY KEY (store_id),
UNIQUE KEY (store_id, manager_staff_id),
CONSTRAINT fk_address_id_address FOREIGN KEY (address_id) REFERENCES address(address_id), 
CONSTRAINT fk_manager_staff_id_staff FOREIGN KEY (manager_staff_id) REFERENCES staff(staff_id)
);