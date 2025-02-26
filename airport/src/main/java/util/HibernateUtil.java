package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	// Instancia estática de SessionFactory para un acceso global
	private static SessionFactory sessionFactory = buildSessionFactory();

	// Método para construir la SessionFactory
	private static SessionFactory buildSessionFactory() {
		try {
			// Cargar configuración desde hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Error en la inicialización de Hibernate: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// Obtener la SessionFactory
	public static SessionFactory getSessionFactory() {
		if(sessionFactory.isClosed())
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	// Método para cerrar la SessionFactory y liberar recursos
	public static void shutdown() {
		getSessionFactory().close(); // Cierra la SessionFactory
	}
}
