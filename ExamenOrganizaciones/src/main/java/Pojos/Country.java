package Pojos;

public class Country {
	private String name;
	private String code;
	private String Capital;
	private float area;
	private int population;
	
	public Country(String name, String code, String capital, float area, int population) {
		this.name = name;
		this.code = code;
		Capital = capital;
		this.area = area;
		this.population = population;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCapital() {
		return Capital;
	}
	public void setCapital(String capital) {
		Capital = capital;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(code);
		builder.append(", Capital=");
		builder.append(Capital);
		builder.append(", area=");
		builder.append(area);
		builder.append(", population=");
		builder.append(population);
		builder.append("]");
		return builder.toString();
	}

}
