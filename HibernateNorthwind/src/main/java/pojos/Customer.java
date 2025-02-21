package pojos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import jakarta.persistence.*;
import util.HibernateUtil;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(length = 5)
    private String customerID;
    
    @Column(nullable = false, length = 40)
    private String companyName;
    
    @Column(length = 30)
    private String contactName;
    
    @Column(length = 15)
    private String country;
    
    @Column(length = 15)
    private String city;
    
    @Column(length = 15)
    private String region;
    
    @Column(length = 10)
    private String postalCode;
    
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    // Getters and Setters
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Customer:" + "\n" + "customerID -> " + customerID + "\n\t" + "companyName -> " + companyName + "\n\t" + "contactName=" + contactName
				+ "\n\t" + "country=" + country ; 
	}
	
	public void add() {
	
		Transaction tx = null;
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
				Session session = factory.openSession()) {
			tx = session.beginTransaction();
			
			session.save(this);
			
			tx.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
	}
	
	public static List<Customer> getCustomers(String pais){
		
		List<Customer> res = new ArrayList<Customer>();
		
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession()) {
			
			
			// esto esta deprecated
//			res = session.createQuery("""
//					from Customer c 
//					where c.country = :pais """)
//					.setParameter("pais", pais)
//					.list();
			
			// asi esta bien
			res = session.createQuery("""
					from Customer c 
					where c.country = :pais """, Customer.class)
					.setParameter("pais", pais)
					.list();
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		
		
		return res.size() != 0 ? res : null;
		
	}
	
	
	// ejercicio 19
// este suppresWarning es para que no me salte el aviso de deprecado del createQuery
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Customer> getCustomers(double importe){
		
		List<Customer> res = new ArrayList<Customer>();
		
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession()) {
			
			res = session.createQuery("""
					from Customer c 
					join c.orders o
					join o.orderDetails d
					group by c
					having sum(d.total) > :valor """)
					.setParameter("valor", importe)
					.list();
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		
		
		return res.size() != 0 ? res : null;
		
	}
	
	// metodo innecesario
	// dada una instancia del cliente ya se dispone de sus pedidos
	// este suppresWarning es para que no me salte el aviso de deprecado del createQuery
	@SuppressWarnings({ "deprecation", "unchecked" })
 	public List<Order> getPedidos(){
		List<Order> res = new ArrayList<Order>();
		Transaction tx = null;
		try (SessionFactory factory = HibernateUtil.getSessionFactory();
				Session session = factory.openSession()) {
			
			res = session.createQuery(
					"from Order " +
					"where customer = :cliente")
					.setParameter("cliente", this)
					.list();
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return res;
	}
	

}




