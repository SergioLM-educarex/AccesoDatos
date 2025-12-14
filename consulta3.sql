SELECT 
    r.nombre AS receta_nombre,
    r.origen_id,
    r.tipo
FROM receta r
JOIN ingrediente i ON i.receta_id = r.id
WHERE i.nombre = 'Tomate';
