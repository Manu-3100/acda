package pojos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@Column(name = "ID")
    private Integer id;
	
	@Column(name = "LastName", nullable = false, length = 50)
    private String lastName;
	
	@Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

	@ManyToOne
	@JoinColumn(name = "AddressID", nullable = false)
    private Address address;

	@OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;
}
