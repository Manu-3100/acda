package CapaNegocio;

public class HawaiPizzaBuilder implements PizzaBuilder {
	
	private final Pizza pizza = new Pizza();
	
	@Override
	public void buildMasa() {
		pizza.setMasa("Suave");
	}

	@Override
	public void buildSalsa() {
		pizza.setSalsa("Dulce");
	}

	@Override
	public void buildRelleno() {
		pizza.setRelleno("Chorizo + alcachofas");
	}

	@Override
	public Pizza build() {
		return pizza;
	}
	
	
	
	
	

}
