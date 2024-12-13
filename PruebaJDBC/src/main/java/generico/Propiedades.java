package generico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	
	private static String url;
	private static String usuario;
	
	// este codigo se ejecuta al iniciar la app
	static {
		Properties properties = new Properties();
		
		try (FileInputStream fis = new FileInputStream("target/conexion.properties")) {
			
			properties.load(fis);
			
			url = properties.getProperty("db.url");
			usuario = properties.getProperty("db.user");
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static String getUrl() {
		return url;
	}

	public static String getUsuario() {
		return usuario;
	}

}
