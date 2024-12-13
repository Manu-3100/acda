package main;

import capaNegocio.Customer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Customer.getCustomersByCountry("Spain").forEach(System.out::println);
		
	}

}
