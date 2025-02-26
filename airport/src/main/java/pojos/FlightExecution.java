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

	public FlightExecutionId getId() {
		return id;
	}

	public void setId(FlightExecutionId id) {
		this.id = id;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
    
    
    
    
    
}

