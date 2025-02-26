package main;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojos.Airport;
import pojos.Plane;
import util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		
		Plane avion = null;
		
		try (SessionFactory factory = HibernateUtil.getSessionFactory(); 
				Session session = factory.openSession()) {

			avion = session.get(Plane.class, 1);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		Set<Airport> res = avion.getAirport();
		
		System.out.println();
		
	}
}
