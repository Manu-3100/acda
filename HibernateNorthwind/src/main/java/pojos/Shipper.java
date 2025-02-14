package pojos;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperID;
    
    @Column(nullable = false, length = 40)
    private String companyName;
    
    @OneToMany(mappedBy = "shipper")
    private List<Order> orders;

    // Getters and Setters
	public Integer getShipperID() {
		return shipperID;
	}

	public void setShipperID(Integer shipperID) {
		this.shipperID = shipperID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
