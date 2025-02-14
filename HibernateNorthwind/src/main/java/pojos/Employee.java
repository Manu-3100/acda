package pojos;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;
    
    @Column(nullable = false, length = 20)
    private String lastName;
    
    @Column(nullable = false, length = 10)
    private String firstName;
    
    @ManyToOne
    @JoinColumn(name = "ReportsTo")
    private Employee reportsTo;
    
    @OneToMany(mappedBy = "reportsTo")
    private List<Employee> subordinates;
    
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
    
    @OneToMany(mappedBy = "employee")
    private List<EmployeeTerritory> employeeTerritories;

    // Getters and Setters
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Employee getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Employee reportsTo) {
		this.reportsTo = reportsTo;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<EmployeeTerritory> getEmployeeTerritories() {
		return employeeTerritories;
	}

	public void setEmployeeTerritories(List<EmployeeTerritory> employeeTerritories) {
		this.employeeTerritories = employeeTerritories;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", reportsTo=" + reportsTo + ", subordinates=" + subordinates + "]";
	}
}
