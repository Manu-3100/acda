package pojos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import util.HibernateUtil;

@Entity
@Table(name = "country")
public class Country {
	@Id
    @Column(name = "Code", nullable = false, length = 4)
    private String code;
    
    @Column(name = "Name", nullable = false, length = 35)
    private String name;
    
    @Column(name = "Capital", length = 35)
    private String capital;
    
    @Column(name = "Area")
    private Float area;
    
    @Column(name = "Population")
    private Integer population;
    
    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private Set<IsMember> memberships;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Set<IsMember> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<IsMember> memberships) {
		this.memberships = memberships;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", capital=");
		builder.append(capital);
		builder.append(", area=");
		builder.append(area);
		builder.append(", population=");
		builder.append(population);
		builder.append(", memberships=");
		builder.append(memberships);
		return builder.toString();
	}

	public void updateLanguage(float threshold, String newLanguageName, Float newLanguaPercentage)  {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Transaction tx = null;
		
		try (Session session = factory.openSession()) {
			
			tx = session.beginTransaction();
			
			List<Language> listaLenguajes = getLanguages();
			
			for(Language l : listaLenguajes) {
				if(l.getPercentage() < threshold) {
					session.delete(l);
				}
			}
			
			Language nuevoL = new Language();
			nuevoL.setCountry(this);
			nuevoL.setName(newLanguageName);
			nuevoL.setPercentage(newLanguaPercentage);
			
			session.save(nuevoL);
			
			tx.commit();
			
			// este catch a parte de las excepciones de hibernate tambien va a recoger
			// la excepcion del metodo add()
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (HibernateException  e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		}
	}
	
	public List<Language> getLanguages() {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		List<Language> res = null;
		
		try (Session session = factory.openSession()) {
			res = new ArrayList<Language>();
			res = session.createQuery("""
					FROM Language l
					WHERE l.country = :pais
					""", Language.class)
						.setParameter("pais", this)
						.list();
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		return (res != null)? res : null ;
	}
	
	public Set<Country> getPaisesFrontera(){
		SessionFactory factory = HibernateUtil.getSessionFactory();
		List<Country[]> lista = null;
		Set<Country> res = null;
		
		try (Session session = factory.openSession()) {
			res = new HashSet<Country>();
			lista = session.createQuery("""
					Select b.country1, b.country2
					FROM Border b
					WHERE b.country1 = :pais
					OR
						  b.country2 = :pais
					""", Country[].class)
						.setParameter("pais", this)
						.list();
			
			for(Country[] c : lista) {
				if( !c[0].equals(this)) 
					res.add(c[1]);
				else 
					res.add(c[0]);
			}
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		return (res != null)? res : null ;
		
	}
	
	public Country add() {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Transaction tx = null;
		
		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			
			session.save(this);
			
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
		return (this != null)? this : null ;
	}
	
	public static Country get(String code) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Country c = null;
		
		try (Session session = factory.openSession()) {
			
			c = session.get(Country.class, code);
			
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		
		return (c != null) ? c : null;
	}
	
	public void delete() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Transaction tx = null;
		
		try (Session session = factory.openSession()) {
			
			tx = session.beginTransaction();
			
			session.createQuery("""
					DELETE FROM IsMember i
					WHERE i.country = :pais
					""")
				.setParameter("pais", this)
				.executeUpdate();
			
			session.createQuery("""
					DELETE FROM Country c
					WHERE c.code = :code
					""")
				.setParameter("code", this.code)
				.executeUpdate();
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