package pojos;

import jakarta.persistence.*;

@Entity
@Table(name = "Region")
public class Region {
    @Id
    private Integer regionID;
    
    @Column(nullable = false, length = 50)
    private String regionDescription;

    // Getters and Setters
	public Integer getRegionID() {
		return regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}

	public String getRegionDescription() {
		return regionDescription;
	}

	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}

	@Override
	public String toString() {
		return "Region [regionID=" + regionID + ", regionDescription=" + regionDescription + "]";
	}
}