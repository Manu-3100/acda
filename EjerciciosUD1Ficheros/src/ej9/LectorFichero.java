package ej9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class LectorFichero implements Iterable<String>{
	
	private List<String> lineas;
	
	public LectorFichero(String nombreFichero){

		try(BufferedReader bf = new BufferedReader(new FileReader(nombreFichero))) {
			
			lineas = bf.lines().collect(Collectors.toList());
			// aunque List tiene el método iterator() para obtener un iterador lo vamos a programar
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public LectorFichero(File fichero){
		new LectorFichero(fichero.getPath());
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterador();
	}

	
	private class Iterador implements Iterator<String> {

		private int id;
		
		@Override
		public boolean hasNext() {
			return id < lineas.size();
		}

		@Override
		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			return lineas.get(id++);
		}
		
	}
	
	
	
	
	
	
	
}
