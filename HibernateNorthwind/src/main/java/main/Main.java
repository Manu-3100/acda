package main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.build.AllowSysOut;

import util.HibernateUtil;
import pojos.*;

public class Main {

	public static void main(String[] args) {

		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {

			Customer customer = session.get(Customer.class, "ALFKI");
			customer.getPedidos().forEach(System.out::println);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

//		Customer c = new Customer();
//		c.setCompanyName("Demoliciones Nadir");
//		c.setCountry("España");
//		c.setCity("Castro");
//		c.setRegion("MS");
//		c.setPostalCode("27800");
//		c.setCustomerID("ASDLK");
//		//c.add();
//		
//		c.getPedidos().forEach(System.out::println);
//		Product p = Product.get("Chai");
//		System.out.println(p);
//		Transaction tx = null;
//		try (SessionFactory factory = HibernateUtil.getSessionFactory(); Session session = factory.openSession()) {
//
//			Customer customer = session.get(Customer.class, "ALFKI");
//			customer.setCountry("España");
//			
//			System.out.println("!-----------------------------------------!");
//			if (customer != null) {
//				tx = session.beginTransaction();
//				
//				customer.setContactName("Carlos");
//				System.out.println(customer);
//				tx.commit();
//			}
//			else
//				System.out.println("No se encontró al cliente");
//			
//			
//			
		/**
		 * Impresón del nombre de los empleados que atendieron al cliente al hacer cada
		 * uno de los pedidos.
		 */
//			List<Order> pedidos = customer.getOrders();
//			for (Order pedido : pedidos)
//				System.out.println(pedido.getEmployee().getFirstName());
//
//			System.out.println("      !.----------.----------.-----------.----------.!      ");
//
//			List<Product> products = session.createQuery("from Product").list();
//			products.forEach(System.out::println);
//
//		} catch (HibernateException e) {
//			System.err.println(e.getMessage());
//			tx.rollback
//		} finally {
//			HibernateUtil.shutdown();
//		}

//		OrderDetail od = OrderDetail.get(10248, 11);
//		System.out.println(od);

	}

}
