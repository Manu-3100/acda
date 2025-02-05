PRAGMA foreign_keys = ON; -- Activa claves foráneas. Desactivadas por defecto.

CREATE TABLE Ciudades (
    idCiudad INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    pais TEXT NOT NULL
);

CREATE TABLE RutaFerroviarias (
    idRuta INTEGER PRIMARY KEY AUTOINCREMENT,
    idCiudadInicio INTEGER NOT NULL,
    idCiudadFin INTEGER NOT NULL,
    distancia_km REAL NOT NULL, 
    CONSTRAINT FK_RutaFerroviaria_CiudadInicio FOREIGN KEY (idCiudadInicio) REFERENCES Ciudades(idCiudad),
    CONSTRAINT FK_RutaFerroviaria_CiudadFin FOREIGN KEY (idCiudadFin) REFERENCES Ciudades(idCiudad),
    CONSTRAINT CHK_Ciudades_Distintas CHECK (idCiudadInicio <> idCiudadFin)
);

CREATE TABLE Trenes (
    idTren INTEGER PRIMARY KEY AUTOINCREMENT,
    modelo TEXT NOT NULL,
    capacidad INTEGER NOT NULL
);

CREATE TABLE Tren_Ruta (
    idTren INTEGER NOT NULL,
    idRuta INTEGER NOT NULL,
    horario_salida TEXT NOT NULL, -- SQLite no tiene TIME, se usa TEXT en formato 'HH:MM:SS'
    horario_llegada TEXT NOT NULL,
    PRIMARY KEY (idTren, idRuta),
    CONSTRAINT FK_TrenRuta_Tren FOREIGN KEY (idTren) REFERENCES Trenes(idTren),
    CONSTRAINT FK_TrenRuta_Ruta FOREIGN KEY (idRuta) REFERENCES RutaFerroviarias(idRuta)
);

CREATE TABLE Clientes (
    idCliente INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    telefono TEXT
);

CREATE TABLE Viajes (
    idRuta INTEGER NOT NULL,
    idTren INTEGER NOT NULL,
    fecha_viaje TEXT NOT NULL, -- La fecha del viaje en formato 'YYYY-MM-DD'
    CONSTRAINT PK_Viaje PRIMARY KEY (idRuta, idTren, fecha_viaje), 
    CONSTRAINT FK_Viaje_TrenRuta FOREIGN KEY (idTren, idRuta) REFERENCES Tren_Ruta(idTren, idRuta)
);


CREATE TABLE Billetes (
    idBillete INTEGER PRIMARY KEY AUTOINCREMENT,
    idCliente INTEGER NOT NULL,
    idRuta INTEGER NOT NULL,
	idTren INTEGER NOT NULL,
    fecha_viaje TEXT NOT NULL, -- La fecha del viaje en formato 'YYYY-MM-DD'
    asiento INTEGER NOT NULL, -- El número de asiento asignado
    CONSTRAINT FK_Billete_Cliente FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),  -- Clave foránea a Cliente
    CONSTRAINT FK_Billete_Viaje FOREIGN KEY (idRuta, idTren, fecha_viaje) REFERENCES Viajes(idRuta, idTren, fecha_viaje)  -- Clave foránea a Viaje
);


