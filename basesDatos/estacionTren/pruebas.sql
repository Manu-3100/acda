SELECT idRuta, distancia_km FROM RutaFerroviarias r
	WHERE 
			r.idCiudadInicio = (Select idCiudad FROM Ciudades WHERE nombre = "Madrid")
		AND 
			r.idCiudadFin = (Select idCiudad FROM Ciudades WHERE nombre = "Barcelona")
UNION
SELECT idRuta, distancia_km FROM RutaFerroviarias r
	WHERE 
			r.idCiudadInicio = (Select idCiudad FROM Ciudades WHERE nombre = "Barcelona")
		AND 
			r.idCiudadFin = (Select idCiudad FROM Ciudades WHERE nombre = "Madrid");
			
INSERT INTO RutaFerroviarias
	(idCiudadInicio, idCiudadFin, distancia_km)
VALUES
	(1, 2, 30);
	
SELECT * FROM Ciudades;
SELECT * FROM RutaFerroviarias;
