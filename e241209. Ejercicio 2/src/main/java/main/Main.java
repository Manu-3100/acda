package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import capaNegocio.Analisis;
import capaNegocio.Dependency;
import capaNegocio.Library;

public class Main {

	private final static String POM = "target/pom.xml";
	private final static String VULNERABILIDADES = "target/vulnerabilidades.xml";
	
	public static void main(String[] args) {
		Analisis analisis = new Analisis();
		
		Map<String, List<String>> map = analisis.verificar(POM, VULNERABILIDADES);
		
		for(String dependencia: map.keySet()) {
			List<String> vulnerabilidades = map.get(dependencia);
			
			System.out.println(dependencia);
			vulnerabilidades.forEach(vulnerabilidad -> System.out.println("\t" + vulnerabilidad));
		}
		/* Deberá imprimir:
		junit
			4.13
			4.0
		postgresql
			42.5.0
		jackson-databind
			2.13.4
		*/
	}
}