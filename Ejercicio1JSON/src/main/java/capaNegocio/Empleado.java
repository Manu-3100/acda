package capaNegocio;

import java.util.ArrayList;
import java.util.Arrays;

public class Empleado {

	private int id;
	private String name;
	private boolean permanent;
	private Address addres;
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private String role;
	private ArrayList<String> cities = new ArrayList<String>();
	private Property properties;
	
	public Empleado(int id, String name, boolean permanent, Address addres, ArrayList<Integer> numbers, String role,
			ArrayList<String> cities, Property properties) {
		this.id = id;
		this.name = name;
		this.permanent = permanent;
		this.addres = addres;
		this.numbers = numbers;
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
	public Address getAddres() {
		return addres;
	}
	public void setAddres(Address addres) {
		this.addres = addres;
	}
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}
	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Empleado => id:").append(id).append(", name: ").append(name).append(", permanent: ")
				.append(permanent).append(", addres: ").append(addres).append(", numbers: ").append(numbers)
				.append(", role: ").append(role).append(", cities: ").append(cities).append(", properties: ")
				.append(properties);
		return builder.toString();
	}
	
	public static Empleado create() {
		Address address = new Address("C\\ Ángelo Colocci, s/n", "Lugo", 27002);
		
		ArrayList<Integer> phoneNumbers = new ArrayList<> (Arrays.asList(666666666, 999999999));
		ArrayList<String> cities = new ArrayList<> (Arrays.asList("Lugo", "Valencia"));
		Property properties = new Property("20", "40000");
		
		return new Empleado(1, "Kevin", true, address, phoneNumbers, "Programador", cities, properties);
		
		
		
	}
	
	
}
