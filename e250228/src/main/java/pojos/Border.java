package pojos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borders")
public class Border {
    @EmbeddedId
    private BorderId id;
    
    @Column(name = "Length")
    private Float length;

	public BorderId getId() {
		return id;
	}

	public void setId(BorderId id) {
		this.id = id;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}
	// Asociación al primer país (Country1)
    @ManyToOne
    @JoinColumn(name = "Country1", referencedColumnName = "Code", insertable = false, updatable = false)
    private Country country1;
    
    // Asociación al segundo país (Country2)
    @ManyToOne
    @JoinColumn(name = "Country2", referencedColumnName = "Code", insertable = false, updatable = false)
    private Country country2;
    
    public Border() {
    }
    
    public Border(BorderId id, Float length) {
        this.id = id;
        this.length = length;
    }
	
}