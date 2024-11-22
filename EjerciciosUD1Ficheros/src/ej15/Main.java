package ej15;

public class Main {

	public static void main(String[] args) {
		
		Cotizaciones cotizaciones = new Cotizaciones();
		
		cotizaciones.leerUltimas();
//		cotizac iones.grabar();
		cotizaciones.leer("2024-10-28").forEach(System.out::println);
	}

}
