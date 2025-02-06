package capaNegocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Trenes {
	static final String db = "Trenes.db";
	static final String dbUrl = "jdbc:sqlite:" + db;

	public static boolean existeBD() {
		File dbFile = new File(db);
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
		try (Connection con = DriverManager.getConnection(dbUrl); Statement stmt = con.createStatement()) {
			String cadenaSQL = leerArchivos(ruta);
			// para crear varias tablas o hacer varias inserciones no sirve el
			// stmt.execute()
			// tiene que ser un ex stmt.executeUpdate()
			// para insertar datos igual
			stmt.executeUpdate(cadenaSQL);
			System.out.println("Base de datos creada");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void insertarDatos(String ruta) {
		try (Connection con = DriverManager.getConnection(dbUrl); Statement stmt = con.createStatement()) {
			String cadenaSQL = leerArchivos(ruta);
			stmt.executeUpdate(cadenaSQL);
			System.out.println("Datos insertados");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void getRutas(String ciudad1, String ciudad2) {

		String cadenaSQL = """
				SELECT idRuta, distancia_km FROM RutaFerroviarias r
					WHERE
						r.idCiudadInicio = (Select idCiudad FROM Ciudades WHERE nombre = ?)
					AND
						r.idCiudadFin = (Select idCiudad FROM Ciudades WHERE nombre = ?)
				UNION
				SELECT idRuta, distancia_km FROM RutaFerroviarias r
					WHERE
						r.idCiudadFin = (Select idCiudad FROM Ciudades WHERE nombre = ?)
					AND
						r.idCiudadInicio = (Select idCiudad FROM Ciudades WHERE nombre = ?)
		""";
		
		try (Connection con = DriverManager.getConnection(dbUrl); 
				PreparedStatement pstmt = con.prepareStatement(cadenaSQL)) {
			pstmt.setString(1, ciudad1);
			pstmt.setString(2, ciudad2);
			pstmt.setString(3, ciudad1);
			pstmt.setString(4, ciudad2);
			
			pstmt.execute();
			
			ResultSet rs = pstmt.getResultSet();
			
			while (rs.next()) {
				System.out.println("Ruta: " + rs.getInt(1) + " Distancia " + rs.getDouble(2));
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void addRuta(String ciudadInicio, String ciudadFin, double distancia_km) {
		
		int idCiudadInicio = getIdCiudad(ciudadInicio);
		if(idCiudadInicio == -1) {
			idCiudadInicio = addCiudad(ciudadInicio, "España");
		}
		
		int idCiudadFin = getIdCiudad(ciudadFin);
		if(idCiudadFin == -1) {
			idCiudadFin = addCiudad(ciudadFin, "España");
		}
		
		if (idCiudadInicio == -1 || idCiudadFin == -1) {
			System.err.println("Error al crear ciudad");
			return;
		}
		
		String cadenaSQL = """
				INSERT INTO RutaFerroviarias
					(idCiudadInicio, idCiudadFin, distancia_km)
				VALUES
					(?, ?, ?);
				""";
				
		
		try (Connection con = DriverManager.getConnection(dbUrl); 
				PreparedStatement pstmt = con.prepareStatement(cadenaSQL)) {
			
			pstmt.setInt(1, idCiudadInicio);
			pstmt.setInt(2, idCiudadFin);
			pstmt.setDouble(3, distancia_km);
			pstmt.executeUpdate();
			
			System.out.println("Ruta añadida");
		} catch (SQLException e) {
			System.err.println("Fallo en la inserción de la ruta");
			System.err.println(e.getMessage());
		}
	}
	
	public static int getIdCiudad(String ciudad) {
		
		String cadenaSQL = """
				SELECT idCiudad FROM Ciudades
				WHERE nombre = ?;
				""";
		
		try (Connection con = DriverManager.getConnection(dbUrl); 
				PreparedStatement pstmt = con.prepareStatement(cadenaSQL)) {
			
			pstmt.setString(1, ciudad);
			pstmt.execute();
			
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				return rs.getInt(1);
			} 
			else {
				return addCiudad(ciudad, "España");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return -1;
		
	}
	
	public static int addCiudad(String nombre, String pais) {

		String cadenaSQL = """
				INSERT INTO Ciudades
					(nombre, pais)
				VALUES
					(?, ?);
				""";
		
		try (Connection con = DriverManager.getConnection(dbUrl); 
				PreparedStatement pstmt = con.prepareStatement(cadenaSQL)) {
			
			pstmt.setString(1, nombre);
			pstmt.setString(2, pais);
			pstmt.executeUpdate();

			return getIdCiudad(nombre);
			
		} catch (SQLException e) {
			System.err.println("Fallo en la inserción de la ciudad");
			System.err.println(e.getMessage());
		}
		return -1;
	}
	
	
	
	
	

}
