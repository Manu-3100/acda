package main;

import CapaNegocio.Director;
import CapaNegocio.PicantePizzaBuilder;
import CapaNegocio.Pizza;
import CapaNegocio.PizzaBuilder;

public class Main {

	public static void main(String[] args) {

		Director director = new Director();
		
		director.setPizzaBuilder(new PicantePizzaBuilder());
		Pizza pizza = director.build();
		
		System.out.println(pizza);
		
		
	}

}
