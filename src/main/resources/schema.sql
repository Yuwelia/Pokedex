CREATE TABLE trainer (id int, name varchar(30), title varchar(15), region varchar(15));

CREATE TABLE pokemon (id int, name varchar(15), trainer_fk int);

CREATE TABLE pokemon_type (type_fk int, pokemon_fk int);

CREATE TABLE type (id int, name varchar(15));
CREATE TABLE strong_against (type_fk int, strong_against_fk int);
CREATE TABLE vulnerable_to (type_fk int, vulnerable_to_fk int);
