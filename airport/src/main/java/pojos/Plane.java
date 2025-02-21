package pojos;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plane")
public class Plane {
    @Id
    @Column(name = "PlaneID")
    private Integer planeId;

    @Column(name = "NoOfSeats", nullable = false)
    private Integer noOfSeats;

    @Column(name = "PlaneType", nullable = false, length = 100)
    private String planeType;

    @Column(name = "PlaneName", nullable = false, length = 100)
    private String planeName;

    @OneToMany(mappedBy = "plane")
    private List<FlightExecution> flightExecutions;

    public Set<Airport> getAirport() {
    	
    	
    	
    }
    
    


}

