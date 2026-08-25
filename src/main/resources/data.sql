INSERT INTO trainer (id, name, title, region)
VALUES
    (1, 'Lola', 'Captain', 'Alola'),
    (2, 'N (Natural Harmonia Gropius)', 'König', 'Einall'),
    (3, 'Serena', NULL, 'Kalos'),
    (4, 'Amalia', 'Meteoraner', 'Hoenn'),
    (5, 'Xenia', 'Wettbewerbssternchen', 'Hoenn'),
    (6, 'Troy', 'Champ', 'Hoenn');

INSERT INTO pokemon (id, pokedex_number, name, trainer_fk)
VALUES
    ((SELECT NEXTVAL('pokemon_seq')), 778, 'Mimigma', 1),
    ((SELECT NEXTVAL('pokemon_seq')), 643, 'Reshiram', 2),
    ((SELECT NEXTVAL('pokemon_seq')), 700, 'Feelinara', 3),
    ((SELECT NEXTVAL('pokemon_seq')), 384, 'Rayquaza', 4),
    ((SELECT NEXTVAL('pokemon_seq')), 293, 'Flurmel', 4),
    ((SELECT NEXTVAL('pokemon_seq')), 334, 'Altaria', 5),
    ((SELECT NEXTVAL('pokemon_seq')), 376, 'Metagross', 6);

INSERT INTO type (id, name)
VALUES
    (1, 'Feuer'),
    (2, 'Wasser'),
    (3, 'Elektro'),
    (4, 'Pflanze'),
    (5, 'Käfer'),
    (6, 'Gestein'),
    (7, 'Stahl'),
    (8, 'Kampf'),
    (9, 'Geist'),
    (10, 'Unlicht'),
    (11, 'Fee'),
    (12, 'Drache'),
    (13, 'Flug'),
    (14, 'Normal'),
    (15, 'Boden'),
    (16, 'Gift'),
    (17, 'Psycho'),
    (18, 'Eis');

INSERT INTO pokemon_type (pokemon_fk, type_fk)
VALUES
    (SELECT id FROM pokemon WHERE pokedex_number = 778, 9),
    (SELECT id FROM pokemon WHERE pokedex_number = 778, 11),

    (SELECT id FROM pokemon WHERE pokedex_number = 643, 1),
    (SELECT id FROM pokemon WHERE pokedex_number = 643, 12),

    (SELECT id FROM pokemon WHERE pokedex_number = 700, 11),

    (SELECT id FROM pokemon WHERE pokedex_number = 384, 12),
    (SELECT id FROM pokemon WHERE pokedex_number = 384, 13),

    (SELECT id FROM pokemon WHERE pokedex_number = 293, 14),

    (SELECT id FROM pokemon WHERE pokedex_number = 334, 12),
    (SELECT id FROM pokemon WHERE pokedex_number = 334, 13),

    (SELECT id FROM pokemon WHERE pokedex_number = 376, 7),
    (SELECT id FROM pokemon WHERE pokedex_number = 376, 17);



INSERT INTO strong_against (type_fk, strong_against_fk)
VALUES
    (1, 4),
    (1, 5),
    (1, 7),
    (1, 18),

    (2, 1),
    (2, 15),
    (2, 6),

    (3, 2),
    (3, 13),

    (4, 2),
    (4, 15),
    (4, 6),

    (5, 4),
    (5, 10),
    (5, 17),

    (6, 1),
    (6, 13),
    (6, 18),
    (6, 5),

    (7, 18),
    (7, 6),
    (7, 11),

    (8, 14),
    (8, 18),
    (8, 10),
    (8, 6),
    (8, 7),

    (9, 9),
    (9, 17),

    (10, 9),
    (10, 17),

    (11, 12),
    (11, 8),
    (11, 10),

    (12, 12),

    (13, 5),
    (13, 4),
    (13, 8),

    (14, NULL),

    (15, 1),
    (15, 3),
    (15, 16),
    (15, 6),
    (15, 7),

    (16, 4),
    (16, 11),

    (17, 8),
    (17, 16),

    (18, 13),
    (18, 15),
    (18, 4),
    (18, 12);

INSERT INTO vulnerable_to (type_fk, vulnerable_to_fk)
VALUES
    (1, 2),
    (1, 15),
    (1, 6),

    (2, 3),
    (2, 4),

    (3, 15),

    (4, 1),
    (4, 13),
    (4, 5),
    (4, 16),
    (4, 18),

    (5, 1),
    (5, 13),
    (5, 6),

    (6, 2),
    (6, 4),
    (6, 8),
    (6, 15),
    (4, 7),

    (7, 1),
    (7, 8),
    (7, 15),

    (8, 13),
    (8, 17),
    (8, 11),

    (9, 9),
    (9, 10),

    (10, 8),
    (10, 5),
    (10, 11),

    (11, 7),
    (11, 16),

    (12, 18),
    (12, 12),
    (12, 11),

    (13, 3),
    (13, 18),
    (13, 6),

    (14, 8),

    (15, 2),
    (15, 4),
    (15, 18),

    (16, 15),
    (16, 17),

    (17, 9),
    (17, 5),
    (17, 10),

    (18, 1),
    (18, 8),
    (18, 6),
    (18, 7);