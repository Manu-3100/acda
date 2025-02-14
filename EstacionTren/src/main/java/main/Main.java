package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import capaNegocio.SQLiteDB;

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
		
	}

}
