package pojos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ismember")
public class IsMember {
    @EmbeddedId
    private IsMemberId id;
    
    @Column(name = "Type", length = 35, nullable = false)
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "Country", insertable = false, updatable = false)
    private Country country;
    
    @ManyToOne
    @JoinColumn(name = "Organization", insertable = false, updatable = false)
    private Organization organization;

	public IsMemberId getId() {
		return id;
	}

	public void setId(IsMemberId id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}