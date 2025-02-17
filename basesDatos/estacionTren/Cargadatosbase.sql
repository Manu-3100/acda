INSERT INTO Ciudad (nombre, pais) VALUES
('Madrid', 'España'),
('Barcelona', 'España'),
('Valencia', 'España'),
('Sevilla', 'España'),
('Bilbao', 'España'),
('Zaragoza', 'España'),
('Málaga', 'España'),
('Alicante', 'España'),
('Granada', 'España'),
('Murcia', 'España'),
('Palma de Mallorca', 'España'),
('Córdoba', 'España'),
('Valladolid', 'España'),
('Salamanca', 'España'),
('San Sebastián', 'España'),
('Oviedo', 'España'),
('Gijón', 'España'),
('Tenerife', 'España'),
('La Coruña', 'España'),
('Vigo', 'España');


INSERT INTO RutaFerroviaria (idCiudadInicio, idCiudadFin, distancia_km) VALUES
(1, 2, 600.5),  -- Madrid ↔ Barcelona
(2, 3, 1030.7), -- Barcelona ↔ Valencia
(3, 4, 350.3),  -- Valencia ↔ Sevilla
(1, 5, 650.8),  -- Madrid ↔ Bilbao
(4, 6, 720.0),  -- Sevilla ↔ Zaragoza
(5, 7, 320.4),  -- Bilbao ↔ Málaga
(6, 8, 195.6),  -- Zaragoza ↔ Alicante
(7, 9, 350.2),  -- Málaga ↔ Granada
(8, 10, 430.0), -- Alicante ↔ Murcia
(9, 11, 120.9); -- Granada ↔ Palma de Mallorca


INSERT INTO Tren (modelo, capacidad) VALUES
('AVE', 200),
('Talgo', 150),
('Euromed', 180),
('Alvia', 160),
('Renfe AVE', 220),
('Cercanías', 120),
('Alvia', 170),
('MD', 100),
('Euromed', 130),
('AVE', 210);


INSERT INTO Tren_Ruta (idTren, idRuta, horario_salida, horario_llegada) VALUES
(1, 1, '08:00', '11:00'), 
(2, 2, '14:00', '18:00'), 
(3, 3, '10:00', '12:30'), 
(4, 4, '09:30', '13:00'), 
(5, 5, '15:00', '18:30'), 
(6, 6, '07:30', '12:00'), 
(7, 7, '11:00', '15:30'), 
(8, 8, '08:30', '10:30'), 
(9, 9, '16:00', '19:00'), 
(10, 10, '12:00', '16:30'); 


INSERT INTO Cliente (nombre, apellido, email, telefono) VALUES
('Juan', 'Pérez', 'juan.perez@example.com', '123456789'),
('Ana', 'Gómez', 'ana.gomez@example.com', '987654321'),
('Carlos', 'López', 'carlos.lopez@example.com', '555123456'),
('María', 'Martínez', 'maria.martinez@example.com', '555987654'),
('Luis', 'Sánchez', 'luis.sanchez@example.com', '666123456'),
('Laura', 'Díaz', 'laura.diaz@example.com', '666654321'),
('Pedro', 'Ramírez', 'pedro.ramirez@example.com', '777123456'),
('Marta', 'Fernández', 'marta.fernandez@example.com', '777654321'),
('José', 'García', 'jose.garcia@example.com', '888123456'),
('Sofía', 'Hernández', 'sofia.hernandez@example.com', '888654321');

INSERT INTO Viaje (idRuta, idTren, fecha_viaje) VALUES
(1, 1, '2025-02-10'),  -- Ruta 1, fecha 2025-02-10, Tren 1
(2, 2, '2025-02-11'),  -- Ruta 2, fecha 2025-02-11, Tren 2
(3, 3, '2025-02-12'),  -- Ruta 3, fecha 2025-02-12, Tren 3
(4, 4, '2025-02-13'),  -- Ruta 4, fecha 2025-02-13, Tren 4
(5, 5, '2025-02-14'),  -- Ruta 5, fecha 2025-02-14, Tren 5
(6, 6, '2025-02-15'),  -- Ruta 6, fecha 2025-02-15, Tren 6
(7, 7, '2025-02-16'),  -- Ruta 7, fecha 2025-02-16, Tren 7
(8, 8, '2025-02-17'),  -- Ruta 8, fecha 2025-02-17, Tren 8
(9, 9, '2025-02-18'),  -- Ruta 9, fecha 2025-02-18, Tren 9
(10, 10, '2025-02-19'); -- Ruta 10, fecha 2025-02-19, Tren 10