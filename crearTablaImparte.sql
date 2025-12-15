CREATE TABLE IF NOT EXISTS imparte (
    id_imparte INT AUTO_INCREMENT PRIMARY KEY,
    id_profesor INT,
    id_asignatura INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    FOREIGN KEY (id_profesor) REFERENCES profesor(id_profesor),
    FOREIGN KEY (id_asignatura) REFERENCES asignatura(id_asignatura)
) ENGINE=InnoDB;