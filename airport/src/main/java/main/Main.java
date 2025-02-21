package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		
		
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
				Session session = factory.openSession()) {
			
			
			session.createQuery("""
					
					""", Object.class);
			
			
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
}
