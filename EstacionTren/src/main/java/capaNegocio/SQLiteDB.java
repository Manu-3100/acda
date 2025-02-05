package capaNegocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class SQLiteDB {

	public class SQLiteExample {
		public static void main(String[] args) {
			// Ruta del archivo SQLite (se creará si no existe)
			String url = "jdbc:sqlite:Estacion.sqlite";
			
			try (Connection con = DriverManager.getConnection(url); Statement stmt = con.createStatement()) {
				String sqlCreate = leerArchivos();
				stmt.execute(sqlCreate);
				String sqlInsert = "INSERT INTO usuarios (nombre) VALUES ('Juan')";
				stmt.execute(sqlInsert);
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
				while (rs.next())
					System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
			} catch (SQLException e) {
				System.out.println("Error en SQLite: " + e.getMessage());
			}
		}
	}
	
	public static String leerArchivos() {
		String res = "";
		try (BufferedReader br = new BufferedReader(new FileReader("D:\\ferdebman\\2DAM\\acda\\basesDatos\\estacionTren\\Cargadatosbase.sql"))) {
			String linea = "";
			while ((linea = br.readLine()) != null) {
				res += linea;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
}
