package pojos;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;
    
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;
    
    @Column
    private java.util.Date orderDate;
    
    @Column
    private java.util.Date requiredDate;
    
    @Column
    private java.util.Date shippedDate;
    
    @ManyToOne
    @JoinColumn(name = "ShipVia")
    private Shipper shipper;
    
    @Column(length = 15)
    private String shipCountry;
    
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    // Getters and Setters
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}

	public java.util.Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(java.util.Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public java.util.Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(java.util.Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}