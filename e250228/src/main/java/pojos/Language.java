package pojos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import util.HibernateUtil;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "Name", nullable = false, length = 50)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "Country", nullable = false)
    private Country country;
    
    @Column(name = "Percentage")
    private Float percentage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
	
	public static void addLanguage(String countryCode, String LanguageName, Float percentage) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Transaction tx = null;
		
		try (Session session = factory.openSession()) {
			
			tx = session.beginTransaction();
			
			Language l = new Language();
			Country c = Country.get(countryCode);
			
			if(c == null)
				throw new Exception("No existe el pais");
			
			l.setName(LanguageName);
			l.setCountry(c);
			l.setPercentage(percentage);
			
			session.save(l);
			
			tx.commit();
		} catch (HibernateException e) {
			try {
				tx.rollback();
			} catch (HibernateException  e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
	}
}