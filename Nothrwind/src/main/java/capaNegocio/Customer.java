package capaNegocio;

public class Customer {
	private String companyName;
	private String contactName;
	private int id;

	public Customer() {
	}

	public Customer(String companyName, String contactName) {
		this.companyName = companyName;
		this.contactName = contactName;
	}
	
	public Customer(String companyName, String contactName, int id) {
		this.companyName = companyName;
		this.contactName = contactName;
		this.id = id;
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
}
