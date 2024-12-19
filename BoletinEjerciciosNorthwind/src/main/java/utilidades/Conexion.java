package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion implements AutoCloseable {

private static Connection con = null;
	
	public static synchronized Connection getConexion() {
			try {
				if(con == null)
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind", "root", "abc123."); // a contraseña na casa e esa, na clase e "" .
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return con;
	}

	@Override
	public void close() throws Exception {
		try {
			con.close();	
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
