package capaNegocio;

public class Address {
	private String street;
	private String city;
	private int zipcode;

	public Address() {
	}

	public Address(String street, String city, int zipCode) {
		this.street = street;
		this.city = city;
		this.zipcode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipCode) {
		this.zipcode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", zipcode=" + zipcode + "]";
	}
}
