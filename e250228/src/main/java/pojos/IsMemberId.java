package pojos;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class IsMemberId implements Serializable {
    @Column(name = "Country", nullable = false, length = 4)
    private String country;
    
    @Column(name = "Organization", nullable = false, length = 12)
    private String organization;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsMemberId that = (IsMemberId) o;
        return Objects.equals(country, that.country) &&
               Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, organization);
    }
}
