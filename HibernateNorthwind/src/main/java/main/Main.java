package main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.build.AllowSysOut;

import util.HibernateUtil;
import pojos.*;

public class Main {

	public static void main(String[] args) {

		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {

			Customer customer = session.get(Customer.class, "ALFKI");

			if (customer != null)
				System.out.println(customer);
			else
				System.out.println("No se encontró al cliente");

			System.out.println("      !.----------.----------.-----------.----------.!     ");

			/**
			 * Impresón del nombre de los empleados que atendieron al cliente al hacer cada
			 * uno de los pedidos.
			 */
			List<Order> pedidos = customer.getOrders();
			for (Order pedido : pedidos)
				System.out.println(pedido.getEmployee().getFirstName());

			System.out.println("      !.----------.----------.-----------.----------.!      ");

			List<Product> products = session.createQuery("from Product").list();
			products.forEach(System.out::println);
			
			

		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		} finally {
			HibernateUtil.shutdown();
		}

	}

}
