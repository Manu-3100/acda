package pojos;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(length = 5)
    private String customerID;
    
    @Column(nullable = false, length = 40)
    private String companyName;
    
    @Column(length = 30)
    private String contactName;
    
    @Column(length = 15)
    private String country;
    
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    // Getters and Setters
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer:" + "\n" + "customerID -> " + customerID + "\n\t" + "companyName -> " + companyName + "\n\t" + "contactName=" + contactName
				+ "\n\t" + "country=" + country ; 
	}
}
