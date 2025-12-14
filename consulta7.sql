
INSERT INTO origen(pais, region)
VALUES ('España', 'Cataluña');

UPDATE receta
SET origen_id = (SELECT id FROM origen WHERE region='Cataluña')
WHERE nombre = 'Sopa de ave';
