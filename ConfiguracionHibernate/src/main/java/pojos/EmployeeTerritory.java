package pojos;

import jakarta.persistence.*;

@Entity
@Table(name = "EmployeeTerritories")
public class EmployeeTerritory {
    @EmbeddedId
    private EmployeeTerritoryId id;
    
    @ManyToOne
    @MapsId("employeeID")
    @JoinColumn(name = "EmployeeID")
    private Employee employee;
    
    @ManyToOne
    @MapsId("territoryID")
    @JoinColumn(name = "TerritoryID")
    private Territory territory;  
}
