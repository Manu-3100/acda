package pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import util.HibernateUtil;

@Entity
@Table(name = "continent")
public class Continent {
	@Id
    @Column(name = "Name", nullable = false, length = 20)
    private String name;
    
    @Column(name = "Area")
    private Float area;
    
    @OneToMany(mappedBy = "continent", fetch = FetchType.EAGER)
    private Set<Encompasses> encompasses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Set<Encompasses> getEncompasses() {
		return encompasses;
	}

	public void setEncompasses(Set<Encompasses> encompasses) {
		this.encompasses = encompasses;
	}
	
	public List<Country> getCountries(){
		
		List <Country> res = new ArrayList<Country>();
		
		for(Encompasses e : this.encompasses) {
			res.add(e.getCountry());
		}
		
		return (res != null) ? res : null;
	}
	
	public static Continent get(String continente) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Continent c = null;
		
		try (Session session = factory.openSession()) {
			
			c = session.get(Continent.class, continente);
			
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
		}
		
		return (c != null) ? c : null;
	}
	
}