package CapaNegocio;

public interface PizzaBuilder {
	
	public void buildMasa();
	public void buildSalsa();
	public void buildRelleno();
	
	public Pizza build();
	
	
	
	
}
