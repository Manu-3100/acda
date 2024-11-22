package ej7;

public class Ej7 {
	public static void main(String[] args) {
		
		// Si no hay nada
		if(args.length == 0) {
			System.err.println("Debe introducir un nombre de fichero");
			System.exit(-1);
		}
		
			ILector lector = new Lector();
			String [] palabras;
		
		// si hay algun parametro mas a parte del nombre del fichero
		if(args.length > 1) {
			
			if(args[1].equals("-M")) {
				lector = new DecoratorMayusculas(lector);
				if(args.length > 2) {
					palabras = new String[args.length - 2];
					System.arraycopy(args, 2, palabras, 0, args.length - 2);
					lector = new DecoratorSome(lector, palabras);
				}
			} else { // Tengo palabras para filtrar pero no esta el parámetro -M
				palabras = new String[args.length - 1];
				System.arraycopy(args, 1, palabras, 0, args.length - 1);
				lector = new DecoratorSome(lector, palabras);
			}
			
			lector.leer(args[0]).forEach(System.out::println);
			
			
		}	
	}	
		
}
