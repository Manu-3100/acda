package capaNegocio;

import java.util.ArrayList;
import java.util.Arrays;

public class Empleado {
	private int id;
	private String name;
	private boolean permanent;
	private Address address;
	private ArrayList<Integer> phoneNumbers = new ArrayList<Integer>();
	private String role;
	private ArrayList<String> cities = new ArrayList<String>();
	private Property properties;
	
	// Constructor obligatorio para el trabajo con Jackson
	public Empleado() {}
	
	// Constructor opcional
	public Empleado(int id, String name, boolean permanent, Address address, ArrayList<Integer> phoneNumbers,
			String role, ArrayList<String> cities, Property properties) {
		this.id = id;
		this.name = name;
		this.permanent = permanent;
		this.address = address;
		this.phoneNumbers = phoneNumbers;
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

	public ArrayList<Integer> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<Integer> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	public Property getProperties() {
		return properties;
	}

	public void setProperties(Property properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", name=" + name + ", permanent=" + permanent + ", address=" + address
				+ ", phoneNumbers=" + phoneNumbers + ", role=" + role + ", cities=" + cities + ", properties="
				+ properties + "]";
	}
	
	public static Empleado create() {
		Address address = new Address("C\\ Ángelo Colocci, s/n", "Lugo", 27002);
		ArrayList<Integer> phoneNumbers = new ArrayList<> (Arrays.asList(640111222, 650222333));
		ArrayList<String> cities = new ArrayList<> (Arrays.asList("Lugo", "A Coruña"));
		Property properties = new Property("20", "40000");
		
		return new Empleado(1, "Kevin", true, address, phoneNumbers, "Programador", cities, properties);
	}
}
