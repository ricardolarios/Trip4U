
-- -----------------------------------------------------
-- Schema Trip4U
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS Trip4U ;

-- -----------------------------------------------------
-- Schema Trip4U
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS Trip4U DEFAULT CHARACTER SET utf8 ;
USE Trip4U ;

-- -----------------------------------------------------
-- Table Trip4U.destination
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.destination ;

CREATE TABLE IF NOT EXISTS Trip4U.destination (
  destination_id INT NOT NULL AUTO_INCREMENT,
  city VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL,
  PRIMARY KEY (destination_id));


-- -----------------------------------------------------
-- Table Trip4U.user
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.user ;

CREATE TABLE IF NOT EXISTS Trip4U.user (
  user_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age INT NOT NULL,
  location INT,
  PRIMARY KEY (user_id),
  CONSTRAINT fk_user1
    FOREIGN KEY (location)
    REFERENCES Trip4U.destination (destination_id));

-- -----------------------------------------------------
-- Table Trip4U.type
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.type ;

CREATE TABLE IF NOT EXISTS Trip4U.type (
  type_id INT NOT NULL AUTO_INCREMENT,
  type_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (type_id));

-- -----------------------------------------------------
-- Table Trip4U.cost
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.cost ;

CREATE TABLE IF NOT EXISTS Trip4U.cost (
  cost_id INT NOT NULL AUTO_INCREMENT,
  value INT NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (cost_id));


-- -----------------------------------------------------
-- Table Trip4U.attraction
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.attraction ;

CREATE TABLE IF NOT EXISTS Trip4U.attraction (
  attraction_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  description VARCHAR(1000) NULL,
  destination_id INT NOT NULL,
  cost_id INT NOT NULL,
  type_id INT NOT NULL,
  PRIMARY KEY (attraction_id),
  CONSTRAINT fk_attraction1
    FOREIGN KEY (destination_id)
    REFERENCES Trip4U.destination (destination_id),
  CONSTRAINT fk_attraction2
    FOREIGN KEY (cost_id)
    REFERENCES Trip4U.cost (cost_id),
  CONSTRAINT fk_attraction3
    FOREIGN KEY (type_id)
    REFERENCES Trip4U.type (type_id));


-- -----------------------------------------------------
-- Table Trip4U.travels
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.travels ;

CREATE TABLE IF NOT EXISTS Trip4U.travels (
  user_id INT NOT NULL,
  destination_id INT NOT NULL,
  PRIMARY KEY (user_id, destination_id),
  INDEX fk_user_has_destination1_idx (destination_id ASC) VISIBLE,
  INDEX fk_user_has_destination_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_user_has_destination
    FOREIGN KEY (user_id)
    REFERENCES Trip4U.user (user_id),
  CONSTRAINT fk_user_has_destination1
    FOREIGN KEY (destination_id)
    REFERENCES Trip4U.destination (destination_id));


-- -----------------------------------------------------
-- Table Trip4U.reviews
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.reviews ;

CREATE TABLE IF NOT EXISTS Trip4U.reviews (
  user_id INT NOT NULL,
  attraction_id INT NOT NULL,
  comment VARCHAR(255) NULL,
  date DATE NULL,
  PRIMARY KEY (user_id, attraction_id),
  INDEX fk_user_has_attraction2_idx (attraction_id ASC) VISIBLE,
  INDEX fk_user_has_attraction1_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_user_has_attraction1
    FOREIGN KEY (user_id)
    REFERENCES Trip4U.user (user_id),
  CONSTRAINT fk_user_has_attraction2
    FOREIGN KEY (attraction_id)
    REFERENCES Trip4U.attraction (attraction_id));


-- -----------------------------------------------------
-- Table Trip4U.user_has_preference
-- -----------------------------------------------------
DROP TABLE IF EXISTS Trip4U.user_has_preference ;

CREATE TABLE IF NOT EXISTS Trip4U.user_has_preference (
  user_id INT NOT NULL,
  preference_id INT NOT NULL,
  level INT(1) NOT NULL,
  PRIMARY KEY (user_id, preference_id),
  CONSTRAINT fk_user_has_interest1
    FOREIGN KEY (user_id)
    REFERENCES Trip4U.user (user_id),
  CONSTRAINT fk_user_has_interest2
    FOREIGN KEY (preference_id)
    REFERENCES Trip4U.type (type_id)),
  CONSTRAINT level_Ck CHECK (level IN (1, 2, 3, 4, 5));




-- ------------------------------------------------------
-- insert into user statements 
-- ------------------------------------------------------

-- get all users and their information
select * from user;

-- select every unique name from type
select distinct type_name from type;

