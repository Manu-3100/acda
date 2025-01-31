DROP database seguridad;
CREATE DATABASE Seguridad;
Use Seguridad;

CREATE TABLE Usuarios (
	nombre varchar(20),
	password varchar(46),
	CONSTRAINT PK_USUARIOS PRIMARY KEY (nombre)
 );