package pojos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import util.HibernateUtil;

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
	/*
	 * Indicamos FetchType.EAGER para que cuando se recupere un Plane
	 * tambien se lean sus vuelos, de otro modo se leerian cuando el
	 * usuario los solicite
	*/
	@OneToMany(mappedBy = "plane", fetch = FetchType.EAGER)
	private List<FlightExecution> flightExecutions;

	public Integer getPlaneId() {
		return planeId;
	}

	public void setPlaneId(Integer planeId) {
		this.planeId = planeId;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public List<FlightExecution> getFlightExecutions() {
		return flightExecutions;
	}

	public void setFlightExecutions(List<FlightExecution> flightExecutions) {
		this.flightExecutions = flightExecutions;
	}

	// ejercicio 3
	public static List<Object[]> getEstadistica(String planeType) {

		List<Object[]>  res = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try (Session session = factory.openSession()) {

			Query q = session.createQuery("""
					SELECT p, round(sum(f.durationMinutes) * 1.0 / 60, 1), sum(r.seats)
					FROM Plane p
					 	LEFT JOIN p.flightExecutions f
						LEFT JOIN f.reservations r
					WHERE p.planeType = :tipo
					GROUP BY p
					ORDER BY 2 DESC
					""", Object[].class);
			
			q.setParameter("tipo", planeType);
			
			res = q.list();
			

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return res;
	}
	
	// ejercicio 4
	public Set<Airport> getAirport() {

		Set<Airport> res = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try (Session session = factory.openSession()) {

			List<Airport[]> lista = session.createQuery("""
					Select f.origin, f.destination
					From Plane p join p.flightExecutions f
					Where p.planeId = :id
					""", Airport[].class).setParameter("id", this.planeId).list();

			res = new HashSet<Airport>();

			for (Airport[] aeropuertos : lista) {
				res.add(aeropuertos[0]);
				res.add(aeropuertos[1]);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return res;
	}

	public Set<Airport> getAirport2() {

		Set<Airport> res = new HashSet<Airport>();

		for (FlightExecution f : flightExecutions) {
			res.add(f.getOrigin());
			res.add(f.getDestination());
		}

		return res;
	}

	
	public static Plane get(int planeId) {
		Plane res = null;
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {

			res = session.get(Plane.class, planeId);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return res;
	}

}
