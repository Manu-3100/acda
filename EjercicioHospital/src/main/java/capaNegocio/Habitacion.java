package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Habitacion {
	
	public static boolean existe(Connection con, int habitacion) {
		boolean res = false;
		try (CallableStatement cstmt = con.prepareCall("{? = call existeHabitacion(?)}")){
			cstmt.setInt(2, habitacion);
			cstmt.registerOutParameter(1, Types.TINYINT);
			cstmt.execute();
			
			res = cstmt.getInt(1) == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public static boolean haySitio(Connection con, int habitacion) {
		boolean res = false;
		try (CallableStatement cstmt = con.prepareCall("{? = call hayCamaLibre(?)}")){
			cstmt.setInt(2, habitacion);
			cstmt.registerOutParameter(1, Types.TINYINT);
			cstmt.execute();
			
			res = cstmt.getInt(1) == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
}
