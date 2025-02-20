package capaNegocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utilidades.Conexion;

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
	
	public static List<Customer> getCustomersByCountry(String pais, double importe) {

		List<Customer> customers = new ArrayList<Customer>();

		String consulta = "Select companyName, contactName from customers"
							+ " inner join orders on orders.CustomerID = customers.CustomerID"
							+ " inner join orderdetails on orderdetails.OrderID = orders.OrderID"
							+ "	where country= \"Spain\" "
								+ " and UnitPrice * Quantity * (1 - Discount) > 30 group by CompanyName;";
		
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
