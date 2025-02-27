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
@Table(name = "address")
public class Address {
	@Id
    @Column(name = "AddressID")
    private Integer addressId;

    @Column(name = "ZIPCode", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "Town", nullable = false, length = 100)
    private String town;

    @Column(name = "Street", nullable = false, length = 100)
    private String street;

    @ManyToOne
    @JoinColumn(name = "CountryCod", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "address")
    private List<Airport> airports;

    @OneToMany(mappedBy = "address")
    private List<Customer> customers;
}

