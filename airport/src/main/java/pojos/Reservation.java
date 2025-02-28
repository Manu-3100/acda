package pojos;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import util.HibernateUtil;

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

    
    
    
	public Reservation() {}		

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public FlightExecution getFlightExecution() {
		return flightExecution;
	}

	public void setFlightExecution(FlightExecution flightExecution) {
		this.flightExecution = flightExecution;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// ejercicio 2
	public static Reservation addReserva (int idCliente, int numAsientos, String flightNo, LocalDateTime deparTime) {
		
		Reservation reserva = null;
		
		Transaction t = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try (Session session = factory.openSession()) {
			
			t = session.beginTransaction();
			
			Customer c = session.get(Customer.class, idCliente);
			FlightExecution f = session.get(FlightExecution.class, new FlightExecutionId(flightNo, Timestamp.valueOf(deparTime)));
			
			if(c == null)
				return null;
			if(f == null) {
				return null;
			}
			if(f.getPlazasLibres() < numAsientos) {
				return null;
			}
			
			
			reserva = new Reservation();
			reserva = setReservationId(Reservation.getKey(session));
			reserva.setCustomer(c);
			reserva.setFlightExecution(f);
			reserva.setSeats(numAsientos);
			
			c.getReservations().add(reserva);
			f.getReservations().add(reserva);
			
			session.save(reserva);
			
			t.commit();
			
		} catch (HibernateException e) {
			try {
				t.rollback();
			} catch ( HibernateException e1) {
				System.err.println(e1.getMessage());
			}
			System.err.println(e.getMessage());
		}
				
		return reserva;
	}
    
    public static int getKey(Session s) throws Exception {
    	try {
			Query q = s.createQuery(""" 
					select max(r.reservationId) + 1
					from Reservation r
					""", Integer.class);
			return (int) q.uniqueResult();
			
		} catch (HibernateException e) {
			throw e;
		}
    }
    
    
    
}
