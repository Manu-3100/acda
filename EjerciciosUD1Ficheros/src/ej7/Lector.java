package ej7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Lector implements ILector{

	@Override
	public List<String> leer(String fileName) {
		
		String linea;
		List<String> lista = new LinkedList<String>() ;
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			
			while((linea = br.readLine()) != null) {
				lista.add(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public List<String> leer(File file) {
		return leer(file.getPath());
		// file.getName() no incluye la ruta, solo el nombre
	}

}
