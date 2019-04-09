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
  cost_id INT NULL,
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
  rating INT(1) NOT NULL,
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
    REFERENCES Trip4U.attraction (attraction_id),
    CONSTRAINT rating_Ck CHECK (rating IN(1, 2, 3, 4, 5)));


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
    REFERENCES Trip4U.type (type_id),
  CONSTRAINT level_Ck CHECK (level IN(1, 2, 3, 4, 5)));


-- ---------------------------------------------------------
-- insert into destination statements
-- ---------------------------------------------------------
 insert into destination (city, country) values 
  ('Rome', 'Italy'),
  ('New York', 'USA'),
  ('Boston', 'USA'),
  ('San franciso', 'USA'),
  ('Berlin', 'Germany'),
  ('London', 'United Kingdom'),
  ('Paris', 'France'),
  ('Cairo', 'Egypt'),
  ('Beijing', 'China'),
  ('Tokyo', 'Japan');

-- ------------------------------------------------------
-- insert into user statements 
-- ------------------------------------------------------
insert into user (name, age, location) values
('Rick Steves', 54, 1),
('Uma Thurman', 48, 3),
('Jaden Smith', 20, 10),
('Betty White', 97, 8),
('Stephen Kaufer', 55, 7),
('Timothee Chalamet', 23, 6);

-- ------------------------------------
-- insert into type statements
-- -------------------------------------
insert into type (type_name) values
('art'),
('history'),
('food'),
('nature'),
('science'),
('music'),
('sports');

-- ---------------------------------------------------------
-- insert into user_has_preference statements
-- ---------------------------------------------------------
insert into user_has_preference values
(1, 1, 5),
(1, 2, 4),
(1, 6, 4),
(2, 3, 5),
(2, 7, 3),
(3, 4, 5),
(4, 1, 1),
(4, 7, 5),
(5, 6, 2),
(5, 2, 2),
(5, 7, 2),
(5, 3, 3),
(6, 1, 5),
(6, 4, 5),
(6, 6, 5);

-- ---------------------------------------------------------
-- insert into cost statements
-- ---------------------------------------------------------

insert into cost values
(1, 1, "low"),
(2, 2, "medium"),
(3, 3, "high");

 -- ---------------------------------------------------------
-- insert into attraction statements
-- ---------------------------------------------------------
insert into type (type_name) values
('art'),
('history'),
('food'),
('nature'),
('science'),
('music'),
('sports');

INSERT into attraction  (name, description, destination_id, type_id) values
('Colloseum', 'an Acient roman fighting pit',1, 7),
('Roman Forum','an Acient forum',1, 2),
('Oh Wow Gelato','I mean it tastes good.', 1, 3),
('Apollo and Daphne','They are statues. Cool!', 1, 1),
('Trevi Fountain','Iconic 18th-century sculpted fountain',1, 2),
('Statue of Liberty','American icon in New York Harbor',2, 2),
('Central Park','Urban Oasis', 2, 4),
('Madison Square Garden','Jam out to your favorite band I guess.', 2, 6),
('Empire State Building','103-story landmark',2, 2),
('Rockefeller Center','Midtown business complex',2, 4),
('Freedom Trail','Route with historic sites & museums',3, 2),
('Chicken Lou\'s','Best Eatary on Campus', 3, 3),
('Museum of Fine Arts, Boston','Impressionist art museum',3, 1),
('Boston Common','Venerable park with historic roots',3, 4), 
('Alcatraz Island','Notorious Island Prison', 4, 2),
('Golden Gate Bridge','Iconic art deco bridge',4, 4),
('Fishermans Wharf','Crab stands, souvenir shops & sea lions',4, 3),
('East Side Gallery','Section of Berlin Wall',5, 2),
('Brandenburg Gate','Classical Archway',5, 2),
('Reichstag Building','Histtoric glass-domed home of parliament',5, 2),
('Big Ben','Iconic clock tower',6, 4),
('Buckingham Palace','Home of British Queen',6, 2),
('London Eye','riverside observation wheel',6, 2),
('Eiffel Tower','19-century tower',7, 4),
('Louvre Museum','Landmark art museum',7, 1),
('Palace of Versailles','Louis XIVs gilded palace & gardens',7, 2),
('Giza Necropolis','Site of the Great Pyramids',8, 2),
('Egyptian Museum','5,000 years of Egyptian history',8, 2),
('Pyramid of Djoser','Pyramid built for pharaoh',8, 4),
('Great Wall of China','Historic Structure',9, 2), 
('The Palace Museum','Palace Complex',9, 1), 
('Temple of Heaven','historic temple complex',9, 2), 
('Tokyo Skytree','Huge Tower with observation deck',10, 4), 
('Senso-ji','Historic temple',10, 2), 
('Meiji Jingu','Famed Shinto Shrine',10, 2);

-- ---------------------------------------------------------
-- insert into reviews statements
-- ---------------------------------------------------------
insert into reviews values
(1, 1, 5, 'cool!', '2005-01-05'),
(1, 3, 4 , 'toss a coin for good luck.', '2000-10-20'),
(1, 5, 1, 'dirty and smelly', '2002-05-15'),
(1, 6, 1, 'not for people scared of heights', '2008-06-16'),
(2, 8, 5, 'fascinating walking history lesson', '2008-08-20'),
(2, 10, 1, 'smelled like illicit substances', '2014-06-30'),
(2, 12, 4, 'great views but very windy', '2013-12-23'),
(2, 13, 5, 'seafood to die for', '2013-12-22'),
(3, 16, 5, 'stunning architecture', '2015-11-09'),
(3, 17, 2, 'under construction', '2012-09-23'),
(3, 18, 5, 'go in the afternoon to see the changing of the guards!', '2017-05-20'),
(3, 19, 4, 'overpriced but still a fun experience', '2018-03-12'),
(3, 20, 5, 'go at sunset and bring a picnic', '2018-04-06'),
(4, 21, 4, 'waited 4 hours to see the Mona Lisa, but worth it', '2019-02-14'),
(4, 22, 5, 'the gardens were breathtaking', '2019-02-15'),
(4, 23, 5, 'like stepping in a time machine', '2016-10-03'),
(4, 24, 2, 'boring!', '2016-11-03'),
(4, 25, 3, 'ugly', '2017-07-07'),
(4, 26, 5, 'bring your walking shoes!', '2018-06-14'),
(4, 27, 4, 'awesome but crowded', '2019-03-24'),
(4, 28, 5, 'loved the dragon pillars!', '2019-04-01'),
(4, 29, 5, 'views worth every penny', '2018-11-08'),
(5, 30, 3, 'not much to do but take a picture and leave', '2018-09-15'),
(5, 31, 3, 'located in the middle of a forest, be prepared!', '2018-03-21'),
(6, 2, 2, 'all broken and decrepid', '2019-01-15'),
(6, 4, 5, 'fascinating fixture of American history', '2019-02-23'),
(6, 7, 1, 'down with capitalism!', '2017-10-14'),
(6, 9, 5, 'fantastic collection for a low price', '2018-08-16'),
(6, 11, 3, 'kinda depressing', '2019-03-26'),
(6, 14, 4, 'sobering history lesson', '2018-07-15'),
(6, 15, 5, 'iconic landmark', '2019-08-18');


 select type_id 
 from user 
	join user_has_preference using (user_id)
    join type on (preference_id = type_id)
    where user_id = 1;
    
select name, description
from attraction
	join type using (type_id)
    join user_has_preference using (type_id)
	where destination_id = 1
    and type_id in (select type_id 
							from user 
							join user_has_preference using (user_id)
							join type on (preference_id = type_id)
							where user_id = 1);
                            
 select *
 from user 
	join user_has_preference using (user_id)
    join type on (preference_id = type_id)
    join attraction using (type_id)
    where user_id = 1 and destination_id = 1
    order by level desc;
 
