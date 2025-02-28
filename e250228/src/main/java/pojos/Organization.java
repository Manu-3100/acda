package pojos;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @Column(name = "Abbreviation", nullable = false, length = 12)
    private String abbreviation;
    
    @Column(name = "Name", nullable = false, length = 80)
    private String name;
    
    @Column(name = "City", length = 35)
    private String city;
    
    @Column(name = "Established")
    private java.util.Date established;
    
    @OneToMany(mappedBy = "organization")
    private Set<IsMember> memberships;

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public java.util.Date getEstablished() {
		return established;
	}

	public void setEstablished(java.util.Date established) {
		this.established = established;
	}

	public Set<IsMember> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<IsMember> memberships) {
		this.memberships = memberships;
	}
}