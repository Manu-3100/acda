package capaNegocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class SQLiteDB {
	
	public static String leerArchivos(String nombre) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("G:\\2DAM\\acda\\basesDatos\\estacionTren\\" + nombre))) {
			String linea = "";
			while ((linea = br.readLine()) != null) {
				sb.append(linea).append("\n");
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return sb.toString();
	}
}
