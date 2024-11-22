package CapaNegocio;

public class Director {

	private PizzaBuilder pizzaBuilder;
	
	public void setPizzaBuilder(PizzaBuilder pb) {
		
		this.pizzaBuilder = pb;
	}
	
	public Pizza build() {
		
		pizzaBuilder.buildMasa();
		pizzaBuilder.buildSalsa();
		pizzaBuilder.buildRelleno();
		
		return pizzaBuilder.build();
	}
	
}
