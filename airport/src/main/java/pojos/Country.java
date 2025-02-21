package pojos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "CountryCod", length = 2)
    private String countryCode;

    @Column(name = "CountryName", nullable = false, length = 100)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<Address> addresses;
}
