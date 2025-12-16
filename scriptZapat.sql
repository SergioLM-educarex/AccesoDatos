
CREATE TABLE IF NOT EXISTS zapato (
id int  auto_increment primary key, 
marca varchar (50),
modelo varchar (50),
tamano varchar (50),
color varchar (50),
stock int, 
precio decimal(10,2)
)Engine innodb;