package ej11;

public class Main {

	public static void main(String[] args) {

//		Cotizaciones.leerUltimas();
//		Cotizaciones.grabarHistorico();
		Cotizaciones.leerHistorico("santander");
		
		Cotizaciones.getCotizaciones().forEach(System.out::println);
		
	}

}
