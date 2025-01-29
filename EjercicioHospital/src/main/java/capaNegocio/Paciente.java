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
	
	public Paciente (int doe_numHistoria, String doe_nome, LocalDate doe_datanac) {
		this.doe_numHistoria = doe_numHistoria;
		this.doe_nome = doe_nome;
		this.doe_datanac = doe_datanac;
	}
	
	public int getDoe_numHistoria() {
		return doe_numHistoria;
	}

	public void setDoe_numHistoria(int doe_numHistoria) {
		this.doe_numHistoria = doe_numHistoria;
	}

	public String getDoe_nome() {
		return doe_nome;
	}

	public void setDoe_nome(String doe_nome) {
		this.doe_nome = doe_nome;
	}

	public LocalDate getDoe_datanac() {
		return doe_datanac;
	}

	public void setDoe_datanac(LocalDate doe_datanac) {
		this.doe_datanac = doe_datanac;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paciente => ");
		builder.append(doe_numHistoria);
		builder.append(", nome => ");
		builder.append(doe_nome);
		builder.append(", data nacemento => ");
		builder.append(doe_datanac);
		return builder.toString();
	}

	public enum ESTADO {
		NOEXISTEPACIENTE,
		NOEXISTEHABITACION,
		NOHAYSITIOHABITACION,
		PACIENTEYAINGRESADO,
		ERRORBD,
		OK
	}
	
	public static boolean existePaciente(Connection con, int paciente) {
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
	
	public static boolean hacerIngreso(int paciente, LocalDate fecha, int habitacion) {

		int filas = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
			
			con.setAutoCommit(false);
			
			if (existePaciente(con, paciente) 				&&
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
			
			if (!existePaciente(con, paciente))
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
	
	public static boolean borrar (int paciente) {
		
		Connection con = null;
		boolean estado = true;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/Hospital", "root", "");
			
			con.setAutoCommit(false);
			
			if(existePaciente(con, paciente)) {
				borrarTratamentos(con, paciente);
				borrarIngreso(con, paciente);
				borrarPaciente(con, paciente);
			} else
				estado = false;
			con.commit();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			estado = false;
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return estado;
	}
	
	private static void borrarTratamentos(Connection con, int paciente) throws SQLException {
//		PreparedStatement pstmt = con.prepareStatement("delete from tratamento where tra_idingreso IN "
//				 + "(Select ing_id from ingreso "
//				 		+ "inner join doente "
//				 			+ "on ing_nhdoente = doe_numhistoria "
//				 		+ "where ing_nhdoente = ?);");
		
		try (PreparedStatement pstmt = con.prepareStatement("DELETE t from tratamento t " +
				"INNER JOIN ingreso i " +
				"ON t.tra_IdIngreso = i.ing_id " +
				"WHERE i.ing_nhDoente = ? ;")){

			pstmt.setInt(1, paciente);
			pstmt.execute();
		}
		
	}
	
	private static void borrarIngreso(Connection con, int paciente) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement("delete from ingreso where ing_nhdoente = ?;")){
			pstmt.setInt(1, paciente);
			pstmt.execute();
		}
	}
	
	private static void borrarPaciente(Connection con, int paciente) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement("delete from doente where doe_numhistoria = ?;")){
			pstmt.setInt(1, paciente);
			pstmt.execute();
		}
	}
}
