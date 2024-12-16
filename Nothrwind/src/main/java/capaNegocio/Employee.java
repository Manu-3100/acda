package capaNegocio;

public class Employee {
	
	private String LastName;
	private String FirstName;
	private String TitleOfCourtesy;
	private String City;
	
	
	public Employee(String lastName, String firstName, String titleOfCourtesy, String city) {
		LastName = lastName;
		FirstName = firstName;
		TitleOfCourtesy = titleOfCourtesy;
		City = city;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getTitleOfCourtesy() {
		return TitleOfCourtesy;
	}
	
	public void setTitleOfCourtesy(String titleOfCourtesy) {
		TitleOfCourtesy = titleOfCourtesy;
	}
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	
	
}
