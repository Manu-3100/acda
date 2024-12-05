package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/Northwind";
		String usuario = "root";
		String password = "";
		
		try (Connection con = DriverManager.getConnection(url, usuario, password); 
			 Statement stmt = con.createStatement();) {
			
			String companyName = "Alfreds Futterkiste";
			String contactName = "Maria Anders";
			
			ResultSet rs = stmt.executeQuery("Select count(*) " +
											 "from customers " +
											 "where CompanyName = '" + companyName + "' and " + 
											 "ContactName = '" + contactName + "'" );						
			rs.next();
			if (rs.getInt(1) != 1) {
				System.out.println("Error de validación");
			}
			else
				System.out.println("Validación correcta");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
