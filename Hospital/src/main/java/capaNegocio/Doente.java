package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class Doente {

	private int doe_numhistoria;
	private String doe_nome;
	private LocalDate doe_datanac;

	public Doente(int doe_numhistoria) {
		this.doe_numhistoria = doe_numhistoria;
	}

	public Doente(int doe_numhistoria, String doe_nome, LocalDate doe_datanac) {
		this.doe_numhistoria = doe_numhistoria;
		this.doe_nome = doe_nome;
		this.doe_datanac = doe_datanac;
	}

	public int getDoe_numhistoria() {
		return doe_numhistoria;
	}

	public void setDoe_numhistoria(int doe_numhistoria) {
		this.doe_numhistoria = doe_numhistoria;
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
		builder.append("paciente [doe_numhistoria=");
		builder.append(doe_numhistoria);
		builder.append(", doe_nome=");
		builder.append(doe_nome);
		builder.append(", doe_datanac=");
		builder.append(doe_datanac);
		return builder.toString();
	}

	public static boolean hacerIngreso(int paciente, LocalDate fecha, int habitacion) {

		int filas = 0;
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");

			con.setAutoCommit(false);

			if (existe(con, paciente) && Habitacion.existe(con, habitacion)
					&& Habitacion.haySitio(con, habitacion)) {

				try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO Ingreso "
						+ "(ing_nhDoente, ing_dataingreso, ing_numHabitacion) " + "VALUES (?, ?, ?);");) {

					pstmt.setInt(1, paciente);
					pstmt.setDate(2, Date.valueOf(fecha));
					pstmt.setInt(3, habitacion);

					filas = pstmt.executeUpdate();

					con.commit();

				} catch (Exception e) {
					System.err.println(e.getMessage());
					try {
						con.rollback();
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		}

		return filas == 1;
	}

	public enum ESTADO {
		NOEXISTEPACIENTE, 
		NOESTAINGRESADO, 
		NOEXISTEHABITACION, 
		NOHAYSITIOHABITACION, 
		ERRORBD, 
		OK
	}

	public static ESTADO hacerIngreso2(int paciente, LocalDate fecha, int habitacion) {

		Connection con = null;
		ESTADO estado = ESTADO.OK;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");

			con.setAutoCommit(false);

			if (!existe(con, paciente))
				estado = ESTADO.NOEXISTEPACIENTE;

			else if (!estaIngresado(con, paciente))
				estado = ESTADO.NOESTAINGRESADO;

			else if (!Habitacion.existe(con, habitacion))
				estado = ESTADO.NOEXISTEHABITACION;

			else if (!Habitacion.haySitio(con, habitacion))
				estado = ESTADO.NOHAYSITIOHABITACION;
			
			else
				try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO Ingreso "
						+ "(ing_nhDoente, ing_dataingreso, ing_numHabitacion) " + "VALUES (?, ?, ?);");) {

					pstmt.setInt(1, paciente);
					pstmt.setDate(2, Date.valueOf(fecha));
					pstmt.setInt(3, habitacion);

					pstmt.executeUpdate();

					con.commit();
				} catch (Exception e) {
					System.err.println(e.getMessage());
					try {
						estado = ESTADO.ERRORBD;
						con.rollback();
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}
				}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		}

		return estado;
	}

	public static boolean existe(Connection con, int paciente) {

		boolean res = false;

		try (CallableStatement cstmt = con.prepareCall("{? = call existePaciente(?)}")) {
			cstmt.setInt(2, paciente);
			cstmt.registerOutParameter(1, Types.TINYINT);
			cstmt.execute();

			res = cstmt.getInt(1) == 1;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public static boolean estaIngresado(Connection con, int paciente) {
		boolean res = false;

		try (CallableStatement cstmt = con.prepareCall("{? = call estaIngresado(?)}")) {
			cstmt.setInt(2, paciente);
			cstmt.registerOutParameter(1, Types.BOOLEAN);
			cstmt.execute();

			res = cstmt.getInt(1) == 1;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
}
