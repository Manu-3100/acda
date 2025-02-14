package main;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import capaNegocio.SQLiteDB;
=======
import capaNegocio.Trenes;
>>>>>>> f30790d46ebec001a25782c80a5622b98cca3e55

public class Main {

	public static void main(String[] args) {

		// System.out.println(SQLiteDB.leerArchivos());
		
		String url = "jdbc:sqlite:Estacion.sqlite";

		try (Connection con = DriverManager.getConnection(url); 
				Statement stmt = con.createStatement()) {
			String sqlCreate = SQLiteDB.leerArchivos("SQLparaLaCreacionDelEsquema.sql");
			stmt.execute(sqlCreate);
			System.out.println("acabou de crear");
			String sqlInsert = SQLiteDB.leerArchivos("Cargadatosbase.sql");
			stmt.execute(sqlInsert);
			System.out.println("acabou de insertar");
		} catch (SQLException e) {
			System.out.println("Error en SQLite: " + e.getMessage());
		}
		
		if(!Trenes.existeBD()) {
			// crear la base de datos si no existe
			Trenes.crearBaseDatos("D:\\ferdebman\\2DAM\\acda\\basesDatos\\estacionTren\\SQLparaLaCreacionDelEsquema.sql");	
			// insertar datos
			Trenes.insertarDatos("D:\\ferdebman\\2DAM\\acda\\basesDatos\\estacionTren\\CargaDatosBase.sql");
		} else {
			System.out.println("Ya esta creada, conectando");
		}
		
		// Trenes.getRutas("Madrid", "Barcelona");
		
		Trenes.addRuta("A Coruña", "Lugo", 100);
		
	}
	
	
	
	
	
}
