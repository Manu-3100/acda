package ej7;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class DecoratorSome extends Decorator {

	private String[] palabras;
	
	public DecoratorSome(ILector lector, String[] palabras) {
		super(lector);
		this.palabras = palabras;
	}

	private boolean contiene(String linea) {
		
		for (String palabra: palabras) {
			if(linea.toLowerCase().contains(palabra.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<String> leer(String fileName) {
		List <String> lista = lector.leer(fileName);
		
		lista = lista.stream()
				.filter(l -> contiene(l))
				.collect(Collectors.toList());
		
		
		return lista;
	}

	@Override
	public List<String> leer(File file) {
		return leer(file.getPath());
	}
}
