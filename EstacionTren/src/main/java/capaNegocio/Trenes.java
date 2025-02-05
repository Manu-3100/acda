package capaNegocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Trenes {

	static final String url = "jdbc:sqlite:Trenes.db";
	
	public static boolean existeBD() {
		File dbFile = new File("Trenes.db");
		return dbFile.exists();
	}
	
	public static String leerArchivos(String ruta) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea;
			while ((linea = br.readLine()) != null)
				sb.append(linea).append("\n");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return sb.toString();
	}
	
	public static void crearBaseDatos(String ruta) {
		try (Connection con = DriverManager.getConnection(url); 
				Statement stmt = con.createStatement()) {
			String cadenaSQL = leerArchivos(ruta);
			// para crear varias tablas o hacer varias inserciones no sirve el stmt.execute()
			// tiene que ser un ex stmt.executeUpdate()
			// para insertar datos igual
			stmt.executeUpdate(cadenaSQL);
			System.out.println("Base de datos creada");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void insertarDatos(String ruta) {
		try (Connection con = DriverManager.getConnection(url); 
				Statement stmt = con.createStatement()) {
			String cadenaSQL = leerArchivos(ruta);
			stmt.executeUpdate(cadenaSQL);
			System.out.println("Datos insertados");		
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	

	private static void rutasEntreCiudad(String ciudad1, String ciudad2) {
		
	}
	
	
	
}
