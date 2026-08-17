CREATE TABLE trainer (id int,
                      name varchar(30),
                      title varchar(30),
                      region varchar(30),
                      PRIMARY KEY (id)
                     );

CREATE TABLE pokemon (id int,
                      pokedex_number int,
                      name varchar(30),
                      trainer_fk int,
                      PRIMARY KEY (id),
                      FOREIGN KEY (trainer_fk) REFERENCES trainer (id)
                     );

CREATE TABLE type (id int,
                   name varchar(15),
                   PRIMARY KEY (id)
);

CREATE TABLE pokemon_type (type_fk int,
                           pokemon_fk int,
                           FOREIGN KEY (type_fk) REFERENCES type (id),
                           FOREIGN KEY (pokemon_fk) REFERENCES pokemon (id)
                          );

CREATE TABLE strong_against (type_fk int,
                             strong_against_fk int,
                             FOREIGN KEY (type_fk) REFERENCES type (id),
                             FOREIGN KEY (strong_against_fk) REFERENCES type (id)
                            );

CREATE TABLE vulnerable_to (type_fk int,
                            vulnerable_to_fk int,
                            FOREIGN KEY (type_fk) REFERENCES type (id),
                            FOREIGN KEY (vulnerable_to_fk) REFERENCES type (id)
                           );

CREATE SEQUENCE "pokemon_seq"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1;
