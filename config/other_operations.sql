TRUNCATE camel;

#INSERT INTO horse (name, age, commands)
#SELECT name, age, commands
#FROM donkey;

DROP TABLE donkey;

RENAME TABLE horse TO horseAndDonkey;

ALTER TABLE cat RENAME COLUMN age TO dateOfBirth;

ALTER TABLE dog RENAME COLUMN age TO dateOfBirth;

ALTER TABLE hamster RENAME COLUMN age TO dateOfBirth;

ALTER TABLE camel RENAME COLUMN age TO dateOfBirth;

#ALTER TABLE horseAndDonkey RENAME COLUMN age TO dateOfBirth;

CREATE TABLE youngAnimal
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        CHAR(32),
    dateOfBirth DATE,
    commands    TEXT,
    age         TEXT
);

DELIMITER $$
CREATE FUNCTION animalAge(dateOfBirth DATE)
    RETURNS TEXT
    DETERMINISTIC
BEGIN
    DECLARE result TEXT DEFAULT '';
    SET result = CONCAT(
            TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()),
            ' years ',
            TIMESTAMPDIFF(MONTH, dateOfBirth, CURDATE()) % 12,
            ' month'
        );
    RETURN result;
END $$
DELIMITER ;

INSERT INTO youngAnimal (name, dateOfBirth, commands, age)
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth)
FROM cat
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) BETWEEN 1 AND 2
UNION ALL
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth)
FROM dog
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) BETWEEN 1 AND 2
UNION ALL
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth)
FROM hamster
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) BETWEEN 1 AND 2
UNION ALL
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth)
FROM horseAndDonkey
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) BETWEEN 1 AND 2;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM cat
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) IN (1, 2);

DELETE FROM dog
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) IN (1, 2);

DELETE FROM hamster
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) IN (1, 2);

DELETE FROM horseAndDonkey
WHERE TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) IN (1, 2);

CREATE TABLE animals
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        CHAR(32),
    dateOfBirth DATE,
    commands    TEXT,
    age         TEXT,
    animal_type ENUM ('cat', 'dog', 'hamster', 'horseAndDonkey', 'youngAnimal') NOT NULL
);

INSERT INTO animals (name, dateOfBirth, commands, age, animal_type)
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth), 'cat'
FROM cat;

INSERT INTO animals (name, dateOfBirth, commands, age, animal_type)
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth), 'dog'
FROM dog;

INSERT INTO animals (name, dateOfBirth, commands, age, animal_type)
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth), 'hamster'
FROM hamster;

INSERT INTO animals (name, dateOfBirth, commands, age, animal_type)
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth), 'horseAndDonkey'
FROM horseAndDonkey;

INSERT INTO animals (name, dateOfBirth, commands, age, animal_type)
SELECT name, dateOfBirth, commands, animalAge(dateOfBirth), 'youngAnimal'
FROM youngAnimal;

CREATE TABLE actual_animals
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        CHAR(32),
    dateOfBirth DATE,
    commands    TEXT,
    animal_type ENUM ('cat', 'dog', 'hamster', 'horse', 'donkey', 'camel') NOT NULL
);

CREATE TABLE horse
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     CHAR(32),
    dateOfBirth      DATE,
    commands TEXT
);

CREATE TABLE donkey
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     CHAR(32),
    dateOfBirth      DATE,
    commands TEXT
);

INSERT INTO horse (name, dateOfBirth, commands)
VALUES ('Prinz', '2023-01-15', ''),
       ('Moon', '2020-05-10', 'aport'),
       ('Tyzemec', '2018-10-11', 'voice, eat, run'),
       ('Queen', '2017-03-13', 'eat, aport, run'),
       ('Greta', '2016-10-05', 'voice, eat, jump');

INSERT INTO camel (name, dateOfBirth, commands)
VALUES ('Mystafa', '2023-01-01', ''),
       ('Zyrab', '2015-10-10', 'eat'),
       ('Achilles', '2019-10-11', 'voice, eat'),
       ('Charles', '2001-03-13', 'voice, run'),
       ('Merlin', '2008-10-05', 'voice, eat, run');

INSERT INTO donkey (name, dateOfBirth, commands)
VALUES ('Ace', '2023-01-11', ''),
       ('Crown', '2018-03-10', 'run'),
       ('Tyzemec', '2010-10-11', 'voice, eat'),
       ('Ia', '2021-07-13', 'voice, eat, run'),
       ('Shrek', '2019-11-07', 'voice, eat, fight');

INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type)
SELECT name, dateOfBirth, commands, 'cat'
FROM cat;

INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type)
SELECT name, dateOfBirth, commands, 'dog'
FROM dog;

INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type)
SELECT name, dateOfBirth, commands, 'hamster'
FROM hamster;

INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type)
SELECT name, dateOfBirth, commands, 'horse'
FROM horse;

INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type)
SELECT name, dateOfBirth, commands, 'camel'
FROM camel;

INSERT INTO actual_animals (name, dateOfBirth, commands, animal_type)
SELECT name, dateOfBirth, commands, 'donkey'
FROM donkey;