CREATE TABLE IF NOT EXISTS ave ( 
id_ave int PRIMARY KEY AUTO_INCREMENT, 
nombre_comun varchar (50),
nombre_cientifico varchar(75),
habitat varchar(50),
envergadura_cm int,
estado_conservacion varchar(50)
)Engine=InnoDB;