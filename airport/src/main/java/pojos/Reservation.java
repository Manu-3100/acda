package pojos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "ReservationID")
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "FlightNo", referencedColumnName = "FlightNo"),
        @JoinColumn(name = "DeparTime", referencedColumnName = "DeparTime")
    })
    private FlightExecution flightExecution;

    @Column(name = "Seats", nullable = false)
    private Integer seats;

    @Column(name = "Comment", length = 1000)
    private String comment;
    


    
    
    
    
}
