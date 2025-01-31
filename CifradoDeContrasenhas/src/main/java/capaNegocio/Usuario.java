package capaNegocio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
	
	private String nombre;
	private String contraseña;
	
	public Usuario() {	}
	
	private Usuario(String nombre, String contraseña) {
		this.nombre = nombre;
		this.contraseña = contraseña;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario: ");
		builder.append(nombre);
		builder.append("contraseña: ");
		builder.append(contraseña);
		
		return builder.toString();
	}
	
	public static Usuario add(String nombre, String password) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/seguridad", "root", "");
				CallableStatement cstmt = con.prepareCall("{call addUsuario(?, ?)}")){
				
			String hashRobusto = Sal.getHashRobusto(password);
			cstmt.setString(1, nombre);
			cstmt.setString(2, hashRobusto);
			
			if (cstmt.executeUpdate() == 1)
				return new Usuario(nombre, hashRobusto );
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static Usuario validar(String nombre, String password) {
		
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/seguridad", "root", "");
				PreparedStatement pstmt = con.prepareStatement("SELECT password FROM usuarios WHERE nombre = ?")){
			
			pstmt.setString(1, nombre);
			ResultSet res = pstmt.executeQuery();
			String salMasPass = "";
			
			if (res.next()) {
				salMasPass = res.getString(1);
			} else {
				return null;
			}
			String contrasenha = Sal.deSalar(salMasPass);
			if (contrasenha.equals(password)) {
				return new Usuario(nombre, password);
			} else {
				return null;
			}
				
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	

}
