package pojos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import util.HibernateUtil;

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

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=");
		builder.append(id);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", reservations=");
		builder.append(reservations);
		return builder.toString();
	}
	
	public static Customer get(int id) {
		Customer res = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try (Session session = factory.openSession()) {

			res = session.get(Customer.class, id);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return res;
	}
	
	
}
