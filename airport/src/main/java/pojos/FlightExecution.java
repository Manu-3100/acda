package pojos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "flightexecution")
public class FlightExecution {
    @EmbeddedId
    private FlightExecutionId id;

    @ManyToOne
    @JoinColumn(name = "PlaneID", nullable = false)
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "Origin", nullable = false)
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "Destination", nullable = false)
    private Airport destination;

    @Column(name = "DurationMinutes", nullable = false)
    private Integer durationMinutes;

    @OneToMany(mappedBy = "flightExecution")
    private List<Reservation> reservations;
}

