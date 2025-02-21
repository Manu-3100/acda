package pojos;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "ICAO_Code", length = 4)
    private String icaoCode;

    @Column(name = "AirportName", nullable = false, length = 100)
    private String airportName;

    @ManyToOne
    @JoinColumn(name = "AddressID", nullable = false)
    private Address address;
    
    
}