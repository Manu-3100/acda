package capaNegocio;

import java.util.ArrayList;

import dao.DAO;

public class Customers extends ArrayList<Customer>{
	
	private DAO dao = new DAO("northwind", "root", "");
	
	public boolean leer() {
		String sql =
			"SELECT CustomerID, CompanyName, ContactName " +
		    "FROM Customers";
		Customer customer;
		
		ArrayList<Object[]> res = 
			(ArrayList<Object[]>) dao.leer(sql);
		
		if (res == null)
			return false;
		else {
			for	(int i = 1; i < res.size(); i++) {
				customer = new Customer();
				
				customer.setCustomerID((String)res.get(i)[0]);
				customer.setCompanyName((String)res.get(i)[1]);
				customer.setContactName((String)res.get(i)[2]);
				
				this.add(customer);
			}
			return true;
		}
	}
}



