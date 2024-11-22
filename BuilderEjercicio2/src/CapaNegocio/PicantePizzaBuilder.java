package CapaNegocio;

public class PicantePizzaBuilder  implements PizzaBuilder {
	
private final Pizza pizza = new Pizza();
	
	@Override
	public void buildMasa() {
		pizza.setMasa("Cocido");
	}

	@Override
	public void buildSalsa() {
		pizza.setSalsa("Picante");
	}

	@Override
	public void buildRelleno() {
		pizza.setRelleno("Pimienta + Salchichon");
	}

	@Override
	public Pizza build() {
		return pizza;
	}
}
