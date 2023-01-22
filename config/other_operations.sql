TRUNCATE camel;

INSERT INTO horse (name, age, commands)
SELECT name, age, commands
FROM donkey;

DROP TABLE donkey;

RENAME TABLE horse TO horseAndDonkey;

ALTER TABLE cat RENAME COLUMN age TO dateOfBirth;

ALTER TABLE dog RENAME COLUMN age TO dateOfBirth;

ALTER TABLE hamster RENAME COLUMN age TO dateOfBirth;

ALTER TABLE camel RENAME COLUMN age TO dateOfBirth;

ALTER TABLE horseAndDonkey RENAME COLUMN age TO dateOfBirth;

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