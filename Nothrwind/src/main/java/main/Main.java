package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import capaNegocio.Employee;
import capaNegocio.Northwind;

public class Main {

	public static void main(String[] args) {
		// prueba ejercicio 1
		// Northwind.getCustomersByCountry("Spain", 1000).forEach(System.out::println);

		// Ejercicio 2
		// System.out.println(Northwind.getNumerosEmpleadosPorPais("UK"));
		
		
		// System.out.println(Northwind.addAlumno("nadir"));
		
		// System.out.println(Northwind.addEmployee("Rosa", "Melano"));
//		Employee empleado = new Employee("IBN Salah", "nadir", "Mrs", "Musulmania");
//		System.out.println(Northwind.addEmployee2(empleado));
		
		// Northwind.getProducts(2).forEach(System.out::println);
		
		// Ejercicio 5
		// System.out.println(Northwind.addEmployeeF("jodete", "acepta"));
		
		// Ejercicio 8
		// Northwind.addEmployee("Nadir", "Ibn Salah");
		// Northwind.removeEmployee(11);
		
		// Ejercicio 9
		
		// System.out.println(Northwind.removeEmployeeConNumeroFilas(13));
		
		// Ejercicio 10
		for (Map.Entry<String, List<String>> entry : Northwind.clientsUnitedPackageConfechas().entrySet()) {
			System.out.print(entry.getKey() + " ");
			 for(String fecha : entry.getValue() ) {
				 System.out.print(fecha + " ");
			 }
			 System.out.println();
		}
		
	}
	
	
}
