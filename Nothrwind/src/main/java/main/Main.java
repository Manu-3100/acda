package main;

import capaNegocio.Employee;
import capaNegocio.Northwind;

public class Main {

	public static void main(String[] args) {
		// prueba ejercicio 1
		// Northwind.getCustomersByCountry("Spain", 1000).forEach(System.out::println);
		
		// System.out.println(Northwind.addAlumno("nadir"));
		
		// System.out.println(Northwind.addEmployee("Rosa", "Melano"));
//		Employee empleado = new Employee("IBN Salah", "nadir", "Mrs", "Musulmania");
//		System.out.println(Northwind.addEmployee2(empleado));
		
		Northwind.getProducts(2).forEach(System.out::println);
		
		
	}
	
	
}
