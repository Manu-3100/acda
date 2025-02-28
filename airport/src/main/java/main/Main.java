package main;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojos.*;
import util.HibernateUtil;

public class Main {

	public static void main(String[] args) {

//		Ejercicio 2
		// Customer cliente = Customer.get(1);
		Customer cliente = null;
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try (Session session = factory.openSession()) {

			cliente = session.get(Customer.class, 15943155);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println(cliente);
		
		
//		Ejercicio 3
//		List<Object[]> lista = Plane.getEstadistica("Airbus");
//
//		for(Object[] datos: lista) {
//			System.out.print( ((Plane) datos[0]).getPlaneName() + " ");
//			System.out.print( datos[1] + " ");
//			System.out.print( datos[2] + " ");
//			System.out.println();
//		}
		
//		Ejercicio 4
//		Plane avion = Plane.get(1);
//		System.out.println(avion.getPlaneName());
//		
//		Set<Airport> res = avion.getAirport();
//		
//		res.forEach(System.out::println);
		
	}

}
