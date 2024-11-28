package capaNegocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Empleado {
	private int id;
	private String name;
	private boolean permanent;
	private Address address;
	private long[] phoneNumbers;
	private String role;
	private List<String> cities;
	private Map<String, String> properties;
	
	
	public Empleado() {	
		
	}
	
	public Empleado(int id, String name, boolean permanent, Address address, ArrayList<Integer> phoneNumbers2, String role,
			List<String> cities, Map<String, String> properties) {
		this.id = id;
		this.name = name;
		this.permanent = permanent;
		this.address = address;
		this.phoneNumbers =  phoneNumbers;
		this.role = role;
		this.cities = cities;
		this.properties = properties;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPermanent() {
		return permanent;
	}
	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long[] getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(long[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public static Empleado create() {
		Address address = new Address("C\\ Ángelo Colocci, s/n", "Lugo", 27002);
		ArrayList<Integer> phoneNumbers = new ArrayList<>(Arrays.asList(888888888, 546546546));
		ArrayList<String> cities = new ArrayList<>(Arrays.asList("Lugo", "Valencia"));
		Property properties = new Property("20", "40000");
		
		return new Empleado(1, "Kevin", true, address, phoneNumbers, "Programador", cities, properties);	
		
	}
	
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", name=" + name + ", permanent=" + permanent + ", address=" + address
				+ ", phoneNumbers=" + Arrays.toString(phoneNumbers) + ", role=" + role + ", cities=" + cities
				+ ", properties=" + properties + "]";
	}
}	