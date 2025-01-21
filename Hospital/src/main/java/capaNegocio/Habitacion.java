package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Habitacion {

	private int hab_numero;
	private int hab_numcamas;
	
	public Habitacion(int hab_numero, int hab_numcamas) {
		this.hab_numero = hab_numero;
		this.hab_numcamas = hab_numcamas;
	}
	
	public int getHab_numero() {
		return hab_numero;
	}
	
	public void setHab_numero(int hab_numero) {
		this.hab_numero = hab_numero;
	}
	
	public int getHab_numcamas() {
		return hab_numcamas;
	}
	
	public void setHab_numcamas(int hab_numcamas) {
		this.hab_numcamas = hab_numcamas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Habitacion [hab_numero=");
		builder.append(hab_numero);
		builder.append(", hab_numcamas=");
		builder.append(hab_numcamas);
		return builder.toString();
	}
	
	public static boolean existe(Connection con, int habitacion) {
boolean res = false; 
		
		try (CallableStatement cstmt = con.prepareCall("{? = call existeHabitacion(?)}")) {
			cstmt.setInt(2, habitacion);
			cstmt.registerOutParameter(1, Types.TINYINT);
			cstmt.execute();
			
			res = cstmt.getInt(1) == 1;
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
	
	public static boolean haySitio(Connection con, int habitacion) {
		boolean res = false; 
		
		try (CallableStatement cstmt = con.prepareCall("{? = call hayCamaLibre(?)}")) {
			cstmt.setInt(2, habitacion);
			cstmt.registerOutParameter(1, Types.TINYINT);
			cstmt.execute();
			
			res = cstmt.getInt(1) == 1;
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
		
	}
}
