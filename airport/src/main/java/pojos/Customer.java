package pojos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@Column(name = "ID")
    private Integer id;
	
	@Column(name = "LastName", length = 50)
    private String lastName;
	
	@Column(name = "FirstName", length = 50)
    private String firstName;

	@Column(name = "Address", length = 50)
    private Address address;

	@OneToMany(mappedBy = "CustomerID")
    private List<Reservation> reservations;
}
