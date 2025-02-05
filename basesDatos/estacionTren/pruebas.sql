SELECT idRuta FROM RutaFerroviarias r
	WHERE 
			r.idCiudadInicio = ANY(Select idCiudad FROM Ciudades WHERE nombre = "Madrid")
		AND 
			r.idCiudadFin = ANY (Select idCiudad FROM Ciudades WHERE nombre = "Barcelona")