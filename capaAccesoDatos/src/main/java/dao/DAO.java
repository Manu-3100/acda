package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DAO {

	private String bd;
	private String usuario;
	private String password;
	private Connection con;

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public DAO(String bd, String usuario, String password) {
		this.bd = bd;
		this.usuario = usuario;
		this.password = password;
	}

	/*
	 * DML: Lenguaje de manipulación de datos 
	 * 			insert, update, delete, merge ...
	 */
	public int ejecutarDML(String sql) {
		int filasAfectadas = -1;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + bd, usuario, password);
				Statement pstmt = con.createStatement();){
			filasAfectadas = pstmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.err.println("error en la dml");
			System.err.println(e.getMessage());
		}
		
		return filasAfectadas;
	}
	
	/*
	 * DQL: Lenguaje de consulta de datos
	 * 
	 */

	public List<Object[]> leer(String sql) {

		List<Object[]> res = new ArrayList<Object[]>();
		Object[] fila;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + bd, usuario, password);
				Statement stmt = con.createStatement();){
			
			ResultSet rs = stmt.executeQuery(sql);
			
			ResultSetMetaData rsMetaData = rs.getMetaData();
			
			fila = new Object[rsMetaData.getColumnCount()];
			
			for (int i = 0; i < fila.length; i++) {
				fila[i] = rsMetaData.getColumnLabel(i + 1);
			}
			res.add(fila);
			
		  	while (rs.next()) {
		  		fila = new Object[rsMetaData.getColumnCount()];
				for (int i = 0; i < fila.length; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				res.add(fila);
			}

		} catch (SQLException e) {
			System.err.println("error en el metodo leer");
			System.err.println(e.getMessage());			
		}
		return res;
	}
}
