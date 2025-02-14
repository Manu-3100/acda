package pojos;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Territories")
public class Territory {
    @Id
    private String territoryID;
    
    @Column(nullable = false, length = 50)
    private String territoryDescription;
    
    @ManyToOne
    @JoinColumn(name = "RegionID", nullable = false)
    private Region region;
    
    @OneToMany(mappedBy = "territory")
    private List<EmployeeTerritory> employeeTerritories;

    // Getters and Setters
	public String getTerritoryID() {
		return territoryID;
	}

	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public String getTerritoryDescription() {
		return territoryDescription;
	}

	public void setTerritoryDescription(String territoryDescription) {
		this.territoryDescription = territoryDescription;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<EmployeeTerritory> getEmployeeTerritories() {
		return employeeTerritories;
	}

	public void setEmployeeTerritories(List<EmployeeTerritory> employeeTerritories) {
		this.employeeTerritories = employeeTerritories;
	}
}