package main;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) {
		Customer cliente = new Customer();
		cliente.setCustomerID("ALFKY");
		if (cliente.leer()) {
			System.out.println(cliente.getCompanyName());
			System.out.println(cliente.getContactName());
		} else
			System.out.println("El cliente no está registrado");
		
		Customers clientes = new Customers();
		clientes.leer();
		
		clientes.forEach(System.out::println);
	}
}