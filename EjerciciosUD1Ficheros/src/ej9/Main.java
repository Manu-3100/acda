package ej9;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		LectorFichero lector = new LectorFichero(args[0]);
		
		Iterator<String> it = lector.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}	
	}
}
