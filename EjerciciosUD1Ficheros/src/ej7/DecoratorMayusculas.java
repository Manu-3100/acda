package ej7;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class DecoratorMayusculas extends Decorator {

	public DecoratorMayusculas(ILector lector) {
		super(lector);
	}

	@Override
	public List<String> leer(String fileName) {
		List <String> lista = lector.leer(fileName);
		
		for(int i = 0; i < lista.size(); i++) {
			lista.set(i, lista.get(i).toUpperCase());
		}
		
		lista = lista.stream()
					 .map(String::toUpperCase)
					 .toList();
		return lista;
	}

	@Override
	public List<String> leer(File file) {
		return leer(file.getPath());
	}

	
}
