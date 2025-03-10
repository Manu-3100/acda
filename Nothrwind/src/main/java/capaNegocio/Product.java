package capaNegocio;

public class Product {

	private int id;
	private String name;
	private double unitPrice;
	
	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Product(String name, double unitPrice) {
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product name=").append(name).append(", unitPrice=").append(unitPrice);
		return builder.toString();
	}
	
	
}
