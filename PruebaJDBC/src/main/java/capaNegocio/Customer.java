package capaNegocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import generico.Conexion;

public class Customer {

	private String companyName;
	private String contactName;

	public Customer() {
	}

	public Customer(String companyName, String contactName) {
		this.companyName = companyName;
		this.contactName = contactName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer -> companyName= ").append(companyName).append(", contactName= ").append(contactName);
		return builder.toString();
	}

	public static List<Customer> getCustomers() {

		List<Customer> customers = new ArrayList<Customer>();

		try (Connection con = Conexion.getConexion();
				Statement stmt = con.createStatement();) {

			try (ResultSet rs = stmt.executeQuery("Select companyName, contactName " + "from customers");) {

				while (rs.next()) {
					customers.add(new Customer(rs.getString(1), rs.getString("contactName")));
				}

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return (customers.isEmpty()) ? null : customers;
	}
	
	public static List<Customer> getCustomersByCountry(String pais) {

		List<Customer> customers = new ArrayList<Customer>();

		try (Connection con = Conexion.getConexion();
				PreparedStatement pstmt = con.prepareStatement("Select companyName, contactName " + "from customers " + "where country=?");) {
			pstmt.setString(1, pais);
			try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					customers.add(new Customer(rs.getString(1), rs.getString("contactName")));
				}

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return (customers.isEmpty()) ? null : customers;
	}

	
	
}
