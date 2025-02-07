package capaNegocio;

import java.util.ArrayList;

import dao.DAO;

public class Customer {
	private String customerID;
	private String companyName;
	private String contactName;
	private DAO dao = new DAO("northwind", "root", "");
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
		return "Customer [CustomerID=" + customerID + ", companyName=" + companyName + ", contactName=" + contactName
				+ "]";
	}

	public Customer() {}
	
	public Customer(String customerID, String companyName, String contactName) {
		this.customerID = customerID;
		this.companyName = companyName;
		this.contactName = contactName;
	}
	
	public boolean grabar() {
		String sql = 
			"INSERT INTO Customers " +
		    "(CustomerID, CompanyName, ContactName) " +
			"VALUES " +
		    "('" + this.customerID + "', " +
			"'" + this.companyName + "', " +
		    "'" + this.contactName + "')";
		return dao.ejecutarDML(sql) > 0;
	}
	
	public boolean leer() {
		String sql = 
			"SELECT CustomerID, CompanyName, ContactName " +
			"FROM Customers " +
			"WHERE CustomerID = '" + this.customerID + "'";
		ArrayList<Object[]> res = (ArrayList<Object[]>) dao.leer(sql);
		
		if (res.size() == 1)
			return false;
		else {
			this.companyName = (String)res.get(1)[1];
			this.contactName = (String)res.get(1)[2];
			
			return true;
		}
	}
}
