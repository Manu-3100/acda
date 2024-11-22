package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

	private static List<Alumno> getAlumnos() {
		List<Alumno> listaAlumnos = new ArrayList<>();

		listaAlumnos.add(new Alumno(1, "Javier Ignacio", "Molina Cano", "Java 8", 7, LocalDate.of(2008, 5, 12)));
		listaAlumnos.add(new Alumno(2, "Lillian Eugenia", "G�mez �lvarez", "Java 8", 10, LocalDate.of(1999, 12, 25)));
		listaAlumnos.add(new Alumno(3, "Sixto Naranjoe", "Mar�n", "Java 8", 8.6, LocalDate.of(2002, 10, 16)));
		listaAlumnos.add(new Alumno(4, "Gerardo Emilio", "Duque Guti�rrez", "Java 8", 10, LocalDate.of(1989, 6, 4)));
		listaAlumnos.add(new Alumno(5, "Jhony Alberto", "S�enz Hurtado", "Java 8", 9.5, LocalDate.of(2006, 11, 29)));
		listaAlumnos.add(new Alumno(6, "Germ�n Antonio", "Lotero Upegui", "Java 8", 8, LocalDate.of(1991, 2, 2)));
		listaAlumnos.add(new Alumno(7, "Oscar Dar�o", "Murillo Gonz�lez", "Java 8", 8, LocalDate.of(1995, 1, 14)));
		listaAlumnos.add(new Alumno(8, "Augusto Osorno", "Palacio Mart�nez", "PHP", 9.5, LocalDate.of(1994, 10, 8)));
		listaAlumnos.add(new Alumno(9, "C�sar Oswaldo", "Alzate Agudelo", "Java 8", 8, LocalDate.of(2003, 12, 20)));
		listaAlumnos.add(new Alumno(10, "Gloria", "Gonz�lez Casta�o", "PHP", 10, LocalDate.of(1998, 7, 6)));
		listaAlumnos.add(new Alumno(11, "Jorge Le�n", "Ruiz Ruiz", "Python", 8, LocalDate.of(1984, 11, 17)));
		listaAlumnos.add(new Alumno(12, "John Jairo", "Duque Garc�a", "Java Script", 9.4, LocalDate.of(1992, 4, 19)));
		listaAlumnos.add(new Alumno(13, "Julio Cesar", "Gonz�lez Casta�o", "C Sharp", 10, LocalDate.of(2005, 6, 25)));
		listaAlumnos.add(new Alumno(14, "Gloria Amparo", "Rodas Monsalve", "Ruby", 7, LocalDate.of(1996, 9, 12)));
		listaAlumnos
				.add(new Alumno(15, "Gabriel Jaime", "Jim�nez G�mez", "Java Script", 10, LocalDate.of(2008, 1, 29)));
		listaAlumnos.add(new Alumno(16, "Juan", "P�rez", "Matem�ticas", 7.5, LocalDate.of(1987, 5, 8)));
		listaAlumnos.add(new Alumno(17, "Mar�a", "Garc�a", "Historia", 8.0, LocalDate.of(1990, 10, 3)));
		listaAlumnos.add(new Alumno(18, "Carlos", "L�pez", "F�sica", 6.3, LocalDate.of(1986, 8, 2)));
		listaAlumnos.add(new Alumno(19, "Ana", "Mart�nez", "Qu�mica", 9.2, LocalDate.of(2001, 3, 11)));
		listaAlumnos.add(new Alumno(20, "Pedro", "Gonz�lez", "Biolog�a", 5.7, LocalDate.of(1989, 3, 5)));
		listaAlumnos.add(new Alumno(21, "Laura", "Hern�ndez", "Ingl�s", 8.4, LocalDate.of(1997, 11, 22)));
		listaAlumnos.add(new Alumno(22, "Jos�", "Rodr�guez", "Filosof�a", 6.9, LocalDate.of(2000, 2, 26)));
		listaAlumnos.add(new Alumno(23, "Luc�a", "Fern�ndez", "Inform�tica", 7.8, LocalDate.of(2009, 12, 6)));
		listaAlumnos.add(new Alumno(24, "Miguel", "S�nchez", "Arte", 9.5, LocalDate.of(1993, 7, 10)));
		listaAlumnos.add(new Alumno(25, "Elena", "Jim�nez", "Matem�ticas", 5.4, LocalDate.of(1991, 8, 31)));
		listaAlumnos.add(new Alumno(26, "David", "Torres", "Historia", 7.2, LocalDate.of(2006, 3, 14)));
		listaAlumnos.add(new Alumno(27, "Sandra", "Ruiz", "F�sica", 6.5, LocalDate.of(1999, 5, 7)));
		listaAlumnos.add(new Alumno(28, "Javier", "Ram�rez", "Qu�mica", 9.1, LocalDate.of(2003, 9, 15)));
		listaAlumnos.add(new Alumno(29, "Patricia", "Moreno", "Biolog�a", 8.7, LocalDate.of(1994, 6, 21)));
		listaAlumnos.add(new Alumno(30, "Adri�n", "D�az", "Ingl�s", 7.6, LocalDate.of(1985, 12, 1)));
		listaAlumnos.add(new Alumno(31, "Claudia", "Mu�oz", "Filosof�a", 6.8, LocalDate.of(2002, 4, 23)));
		listaAlumnos.add(new Alumno(32, "Diego", "Alonso", "Inform�tica", 8.9, LocalDate.of(2004, 10, 5)));
		listaAlumnos.add(new Alumno(33, "Nuria", "Guti�rrez", "Arte", 7.4, LocalDate.of(1995, 3, 16)));
		listaAlumnos.add(new Alumno(34, "Ra�l", "Navarro", "Matem�ticas", 9.8, LocalDate.of(1992, 11, 11)));
		listaAlumnos.add(new Alumno(35, "Beatriz", "Rivas", "Historia", 6.1, LocalDate.of(1996, 2, 28)));
		listaAlumnos.add(new Alumno(36, "Manuel", "Ortega", "F�sica", 5.6, LocalDate.of(2007, 7, 19)));
		listaAlumnos.add(new Alumno(37, "Sara", "Rubio", "Qu�mica", 8.3, LocalDate.of(1990, 1, 25)));
		listaAlumnos.add(new Alumno(38, "Rafael", "Soler", "Biolog�a", 6.9, LocalDate.of(2001, 8, 9)));
		listaAlumnos.add(new Alumno(39, "Carmen", "Mendoza", "Ingl�s", 9.4, LocalDate.of(1998, 12, 13)));
		listaAlumnos.add(new Alumno(40, "Alberto", "Pascual", "Filosof�a", 7.1, LocalDate.of(1993, 4, 30)));
		listaAlumnos.add(new Alumno(41, "Marta", "Castro", "Inform�tica", 5.9, LocalDate.of(2005, 11, 2)));
		listaAlumnos.add(new Alumno(42, "Fernando", "Iglesias", "Arte", 8.5, LocalDate.of(1997, 6, 18)));
		listaAlumnos.add(new Alumno(43, "Isabel", "Vega", "Matem�ticas", 9.0, LocalDate.of(2000, 9, 27)));
		listaAlumnos.add(new Alumno(44, "Oscar", "Dom�nguez", "Historia", 6.2, LocalDate.of(2000, 3, 3)));
		listaAlumnos.add(new Alumno(45, "Alicia", "Prieto", "F�sica", 7.9, LocalDate.of(2007, 4, 7)));

		return listaAlumnos;
	}

	public static void main(String[] args) {

		List<Alumno> listaAlumnos = getAlumnos();

		// Ejercicio 1.a
//    			listaAlumnos.forEach(alumno -> System.out.println(alumno));

		// Ejercicio 1.b
//    			listaAlumnos.forEach(System.out::println);	

		// Ejercicio 2

//    	listaAlumnos.stream()
//    				.filter(e -> e.getEdad() > 18)
//    				.forEach(alumno -> System.out.println(alumno.getNombre()));

		// Ejercicio 3
//    	listaAlumnos.stream()
//    				.filter(e -> e.getNombre().charAt(0) == 'L' ||
//    						e.getNombre().charAt(0) == 'G')
//    				.forEach(System.out::println);    

		// Ejercicio 4

//    	System.out.println(listaAlumnos.stream().count());

		// Ejercicio 5

//    	listaAlumnos.stream()
//    			.filter(e -> e.getNombreCurso() == "PHP" && e.getNota() > 9 )
//    			.forEach(System.out::println);

		// Ejercicio 6
//    	listaAlumnos.stream().limit(5).forEach(System.out::println);

		// Ejercicio 7
//    	System.out.println(
//    		listaAlumnos.stream().min((a1, a2) -> a1.getEdad() - a2.getEdad())
//    	);
//    	
//    	System.out.println(
//    			listaAlumnos.stream().max((a1, a2) -> a1.getEdad() - a2.getEdad())
//    	);

		// Ejercicio 8
//    	System.out.println(listaAlumnos.stream().findFirst());

		// Ejercicio 9
//    	listaAlumnos.stream()
//    			.filter(e -> e.getNombreCurso()
//    			.endsWith("t"))
//    			.forEach(System.out::println);

		// Ejercicio 10
//    	listaAlumnos.stream()
//    				.filter(e -> e.getNombreCurso().contains("a"))
//    				.forEach(System.out::println);

		// Ejercicio 11

//    	listaAlumnos.stream()
//    			.filter(e -> e.getNombres().length() > 10)
//    			.forEach(System.out::println);

		// Ejercicio 12

//    	System.out.println(
//    				listaAlumnos.stream()
//    					.filter(e -> e.getNombreCurso().equals("PHP")).count()
//    			);

		// Ejercicio 13
//    			List<String> apellidos = listaAlumnos.stream()
//    					.map(Alumno::getApellidos)
//    					.collect(Collectors.toList());
//    			
		// Ejercicio 14
//    	
//    			listaAlumnos.stream()
//    						.sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
//    						.limit(5)
//    						.forEach(a -> System.out.println(a.getNombre()));;
		// Ejercicio 15
//    			List<Alumno> alumnos = listaAlumnos.stream()
//    					.sorted(Comparator.comparing(Alumno::getApellidos))
//    					.collect(Collectors.toList());

		// Ejercicio 16
//    			System.out.println(listaAlumnos.stream()
//						.allMatch(a -> a.getNota() >= 4)); 

		// Ejercicio 17
//    			System.out.println(listaAlumnos.stream().anyMatch(a -> "PHP".equals(a.getNombreCurso())));

		// Ejercicio 18
//    			List<Alumno> alumnosJovenes = listaAlumnos.stream()
//    					.filter(alumno -> alumno.getEdad() >= 18 && alumno.getEdad() < 22)
//    					.collect(Collectors.toList());

		// Ejercicio 19
//    			System.out.println(
//    			listaAlumnos.stream()
//    						.filter(a -> a.getNombreCurso().equals("PHP"))
//    						.mapToDouble(Alumno::getNota)
//    						.average());    			
		// Ejercicio 20
//    			Optional<Alumno> a = listaAlumnos.stream()
//    						.max(Comparator.comparingDouble(Alumno::getNota));
//    			System.out.println(a);

//		List<Alumno> lista =
//				listaAlumnos.stream()
//				.collect(Collectors.groupingBy(Alumno::getNota))
//				.entrySet()
//				.stream()
//				.max(Map.Entry.comparingByKey())
//				.map(Map.Entry::getValue)
//				.orElseThrow();
		
		// Ejercicio 21
//		double media = listaAlumnos.stream()
//			.mapToInt(Alumno::getEdad)
//			.average().orElse(-1);
//		System.out.println(media);
		
		// Ejercicio 22
//		Alumno a = listaAlumnos.stream()
//					.sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
//					.skip(1)
//					.findFirst()
//					.orElse(null);
//		System.out.println(a);
		
		// Ejercicio 23
//		Map<String, Double> mapa =
//		 listaAlumnos.stream()
//		 			 .collect(Collectors.toMap(Alumno::getNombre, Alumno::getNota));
//		
//		Iterator it = mapa.entrySet().iterator();
//		Map.Entry<String, Double> entry;
//		while(it.hasNext()) {
//			entry = (Map.Entry<String, Double>)it.next();
//			System.out.println(entry.getKey() + " " + entry.getValue());
//			
//		}
		
		// Ejercicio 24

//				Map<String, List<Alumno>> mapa = 
//				listaAlumnos.stream()
//				.collect(Collectors.groupingBy(Alumno::getNombreCurso));
//		for(Map.Entry<String, List<Alumno>> entry : mapa.entrySet()) {
//			System.out.println(entry.getKey());
//			entry.getValue().forEach(System.out::println);
//		}
		
		// Ejercicio 25
		
//		Map<String, Long> conteo = 
//				listaAlumnos.stream()
//							.collect(Collectors.groupingBy(Alumno::getNombreCurso, Collectors.counting()));
//		for(Map.Entry<String, Long> entry : conteo.entrySet()) {
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}		
		
		// Ejercicio 26
//		Predicate<Alumno> empiezaConP = 
//				a -> a.getNombre().charAt(0)=='P';
//		
//		Predicate<Alumno> longitud = 
//				a -> a.getNombre().length() <= 6;
//				
//		listaAlumnos.stream()
//					.filter(empiezaConP.and(longitud))
//					.forEach(System.out::println);
				
		// Ejercicio 27
//		List<Alumno> nuevaLista = listaAlumnos.stream()
//		.filter(a -> a.getNombreCurso().contains("a"))
//		.collect(Collectors.toList());
		
		// Ejercicio 28
//		listaAlumnos.stream()
//					.map(a -> a.getNombre().toUpperCase())
//					.forEach(System.out::println);
		
		// Ejercicio 29
//		listaAlumnos.stream()
//					.sorted((a, b) -> a.getNombre().compareTo(b.getNombre()))
//					.forEach(a -> System.out.println(a.getNombre()));
		// Ejercicio 30
//		Map<String, Integer> notas = new HashMap<>();
//		notas.put("Daniel", 8);
//		notas.put("José Luis", 8);
//		notas.put("Kevin", 8);
//		notas.put("Martin", 4);
//		
//		List<String> aprobados =
//		notas.entrySet().stream()
//						.filter(a -> a.getValue() >= 5)
//						.map(Map.Entry::getKey)
//						.collect(Collectors.toList());
//		
//		aprobados.forEach(System.out::println);
		
		
	}
}