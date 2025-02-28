package pojos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class BorderId implements Serializable {
    @Column(name = "Country1", nullable = false, length = 4)
    private String country1;
    
    @Column(name = "Country2", nullable = false, length = 4)
    private String country2;
}
