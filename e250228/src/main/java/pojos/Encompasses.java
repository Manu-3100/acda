package pojos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "encompasses")
public class Encompasses {
    @EmbeddedId
    private EncompassesId id;
    
    @Column(name = "Percentage")
    private Float percentage;
    
    @ManyToOne
    @JoinColumn(name = "Continent", insertable = false, updatable = false)
    private Continent continent;
    
    @ManyToOne
    @JoinColumn(name = "Country", insertable = false, updatable = false)
    private Country country;

	public EncompassesId getId() {
		return id;
	}

	public void setId(EncompassesId id) {
		this.id = id;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
