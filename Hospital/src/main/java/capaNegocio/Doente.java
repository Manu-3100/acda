package capaNegocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static void hacerIngreso(int paciente, LocalDate fecha, int habitacion) {
		
		Connection con = null;
		boolean doe;
		boolean hab;
		boolean camasLibres;
		boolean ingreso;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
			PreparedStatement pstmt = con.prepareStatement("Select * from doente where doe_numhistoria = ?");
			
			
			// comprobacion paciente
			pstmt.setInt(1, paciente);
			ResultSet rs =  pstmt.executeQuery();
			
			if(rs.next())
				doe = true;
			else
				doe = false;
			
			// comprobacion habitación
			pstmt = con.prepareStatement("Select * from habitacion where hab_numero = ?");
			pstmt.setInt(1, habitacion);
			rs =  pstmt.executeQuery();
			
			if(rs.next())
				hab = true;
			else
				hab = false;
			
			// comprobacion camas
			pstmt = con.prepareStatement("Select * from habitacion where hab_numero = ?");
			pstmt.setInt(1, habitacion);
			rs =  pstmt.executeQuery();
			
			if(rs.next())
				hab = true;
			else
				hab = false;
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println(e1.getMessage());
			}
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			
		}
		
		return ingreso;
	}
	
	
	
}
