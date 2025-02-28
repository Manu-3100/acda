package pojos;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class EncompassesId implements Serializable {
    @Column(name = "Country", nullable = false, length = 4)
    private String country;
    
    @Column(name = "Continent", nullable = false, length = 20)
    private String continent;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncompassesId that = (EncompassesId) o;
        return Objects.equals(country, that.country) &&
               Objects.equals(continent, that.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, continent);
    }
}
