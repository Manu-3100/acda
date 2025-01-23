package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class Paciente {
	
	private int doe_numHistoria;
	private String doe_nome;
	private LocalDate doe_datanac;
	
	public enum ESTADO {
		NOEXISTEPACIENTE,
		NOEXISTEHABITACION,
		NOHAYSITIOHABITACION,
		PACIENTEYAINGRESADO,
		ERRORBD,
		OK
	}
	
	public static boolean existe(Connection con, int paciente) {
		boolean res = false;
		try (CallableStatement cstmt = con.prepareCall("{? = call existePaciente(?)}")){
			cstmt.setInt(2, paciente);
			cstmt.registerOutParameter(1, Types.TINYINT);
			cstmt.execute();
			
			res = cstmt.getInt(1) == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public static boolean yaIngresado(Connection con, int paciente) {
		boolean res = false;
		try (CallableStatement cstmt = con.prepareCall("{? = call estaIngresado(?)}")){
			cstmt.setInt(2, paciente);
			cstmt.registerOutParameter(1, Types.BOOLEAN);
			cstmt.execute();

			res = cstmt.getBoolean(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public static boolean hacerIngreso(int paciente, LocalDate fecha, int habitacion) {

		int filas = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
			
			con.setAutoCommit(false);
			
			if (existe(con, paciente) 				&&
				Habitacion.existe(con, habitacion)	&&
				Habitacion.haySitio(con, habitacion)) {
				
				try (PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO Ingreso " +
					"(ing_nhDoente, ing_dataingreso, ing_numHabitacion) " +
					"VALUES (?, ?, ?)" ) ) {
				
					pstmt.setInt(1, paciente);
					pstmt.setDate(2, Date.valueOf(fecha));
					pstmt.setInt(3, habitacion);
				
					filas = pstmt.executeUpdate();
				
					con.commit();
				} catch(SQLException e) {
					System.out.println(e.getMessage());
					try {
						con.rollback();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				} 
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch(SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		return filas == 1;
	}
	
	/*
	 * El método "hacerIngreso" devuelve un boolean, por lo que no se sabe, en caso de fallo, 
	 * si el paciente no está registrado, no existe la habitación o si no hay sitio.
	 * Este método devuelve un enumerado indicado qué ocurrió.
	 */
	public static ESTADO hacerIngreso2(int paciente, LocalDate fecha, int habitacion) {
		
		Connection con = null;
		ESTADO estado = ESTADO.OK;
		
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
			
			con.setAutoCommit(false);
			
			if (!existe(con, paciente))
				estado = ESTADO.NOEXISTEPACIENTE;
			else if (Paciente.yaIngresado(con, paciente))
				estado = ESTADO.PACIENTEYAINGRESADO;
			else if (!Habitacion.existe(con, habitacion))
				estado = ESTADO.NOEXISTEHABITACION;
			else if (!Habitacion.haySitio(con, habitacion))
				estado = ESTADO.NOHAYSITIOHABITACION;
			else	
				try (PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO Ingreso " +
					"(ing_nhDoente, ing_dataingreso, ing_numHabitacion) " +
					"VALUES (?, ?, ?)" ) ) {
					
					pstmt.setInt(1, paciente);
					pstmt.setDate(2, Date.valueOf(fecha));
					pstmt.setInt(3, habitacion);
					
					pstmt.executeUpdate();
					
					con.commit();
				} catch(SQLException e) {
					System.out.println(e.getMessage());
					try {
						// Tal y como está hecha la aplicación, solo se devuelve ERRORBD
						// si hay un error en la inserción del ingreso.
						estado = ESTADO.ERRORBD;
						con.rollback();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				} 
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch(SQLException e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		return estado;
	}
}
