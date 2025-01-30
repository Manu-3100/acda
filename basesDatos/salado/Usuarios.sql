CREATE DATABASE Seguridad;
Use Seguridad;

CREATE TABLE Usuarios (
	idPersona int not null auto_increment,
	nombre varchar(20),
	password varchar(46),
	CONSTRAINT PK_USUARIOS PRIMARY KEY (idPersona)
 );