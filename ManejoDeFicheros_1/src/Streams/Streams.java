package Streams;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {
        List<Alumno> listaAlumnos = new ArrayList<>();

		listaAlumnos.add(new Alumno(1, "Javier Ignacio", "Molina Cano", "Java 8", 7, 28));
		listaAlumnos.add(new Alumno(2, "Lillian Eugenia", "G�mez �lvarez", "Java 8", 10, 33));
		listaAlumnos.add(new Alumno(3, "Sixto Naranjoe", "Mar�n", "Java 8", 8.6, 15));
		listaAlumnos.add(new Alumno(4, "Gerardo Emilio", "Duque Guti�rrez", "Java 8", 10, 13));
		listaAlumnos.add(new Alumno(5, "Jhony Alberto", "S�enz Hurtado", "Java 8", 9.5, 15));
		listaAlumnos.add(new Alumno(6, "Germ�n Antonio", "Lotero Upegui", "Java 8", 8, 34));
		listaAlumnos.add(new Alumno(7, "Oscar Dar�o", "Murillo Gonz�lez", "Java 8", 8, 32));
		listaAlumnos.add(new Alumno(8, "Augusto Osorno", "Palacio Mart�nez", "PHP", 9.5, 17));
		listaAlumnos.add(new Alumno(9, "C�sar Oswaldo", "Alzate Agudelo", "Java 8", 8, 26));
		listaAlumnos.add(new Alumno(10, "Gloria", "Gonz�lez Casta�o", "PHP", 10, 28));
		listaAlumnos.add(new Alumno(11, "Jorge Le�n", "Ruiz Ruiz", "Python", 8, 22));
		listaAlumnos.add(new Alumno(12, "John Jairo", "Duque Garc�a", "Java Script", 9.4, 32));
		listaAlumnos.add(new Alumno(13, "Julio Cesar", "Gonz�lez Casta�o", "C Sharp", 10, 22));
		listaAlumnos.add(new Alumno(14, "Gloria Amparo", "Rodas Monsalve", "Ruby", 7, 18));
		listaAlumnos.add(new Alumno(15, "Gabriel Jaime", "Jim�nez G�mez", "Java Script", 10, 18));
		listaAlumnos.add(new Alumno(16, "Juan", "P�rez", "Matem�ticas", 7.5, 20));
		listaAlumnos.add(new Alumno(17, "Mar�a", "Garc�a", "Historia", 8.0, 22));
		listaAlumnos.add(new Alumno(18, "Carlos", "L�pez", "F�sica", 6.3, 21));
		listaAlumnos.add(new Alumno(19, "Ana", "Mart�nez", "Qu�mica", 9.2, 23));
		listaAlumnos.add(new Alumno(20, "Pedro", "Gonz�lez", "Biolog�a", 5.7, 19));
		listaAlumnos.add(new Alumno(21, "Laura", "Hern�ndez", "Ingl�s", 8.4, 20));
		listaAlumnos.add(new Alumno(22, "Jos�", "Rodr�guez", "Filosof�a", 6.9, 24));
		listaAlumnos.add(new Alumno(23, "Luc�a", "Fern�ndez", "Inform�tica", 7.8, 22));
		listaAlumnos.add(new Alumno(24, "Miguel", "S�nchez", "Arte", 9.5, 18));
		listaAlumnos.add(new Alumno(25, "Elena", "Jim�nez", "Matem�ticas", 5.4, 25));
		listaAlumnos.add(new Alumno(26, "David", "Torres", "Historia", 7.2, 20));
		listaAlumnos.add(new Alumno(27, "Sandra", "Ruiz", "F�sica", 6.5, 19));
		listaAlumnos.add(new Alumno(28, "Javier", "Ram�rez", "Qu�mica", 9.1, 23));
		listaAlumnos.add(new Alumno(29, "Patricia", "Moreno", "Biolog�a", 8.7, 22));
		listaAlumnos.add(new Alumno(30, "Adri�n", "D�az", "Ingl�s", 7.6, 21));
		listaAlumnos.add(new Alumno(31, "Claudia", "Mu�oz", "Filosof�a", 6.8, 20));
		listaAlumnos.add(new Alumno(32, "Diego", "Alonso", "Inform�tica", 8.9, 24));
		listaAlumnos.add(new Alumno(33, "Nuria", "Guti�rrez", "Arte", 7.4, 18));
		listaAlumnos.add(new Alumno(34, "Ra�l", "Navarro", "Matem�ticas", 9.8, 23));
		listaAlumnos.add(new Alumno(35, "Beatriz", "Rivas", "Historia", 6.1, 21));
		listaAlumnos.add(new Alumno(36, "Manuel", "Ortega", "F�sica", 5.6, 22));
		listaAlumnos.add(new Alumno(37, "Sara", "Rubio", "Qu�mica", 8.3, 19));
		listaAlumnos.add(new Alumno(38, "Rafael", "Soler", "Biolog�a", 6.9, 25));
		listaAlumnos.add(new Alumno(39, "Carmen", "Mendoza", "Ingl�s", 9.4, 20));
		listaAlumnos.add(new Alumno(40, "Alberto", "Pascual", "Filosof�a", 7.1, 22));
		listaAlumnos.add(new Alumno(41, "Marta", "Castro", "Inform�tica", 5.9, 21));
		listaAlumnos.add(new Alumno(42, "Fernando", "Iglesias", "Arte", 8.5, 19));
		listaAlumnos.add(new Alumno(43, "Isabel", "Vega", "Matem�ticas", 9.0, 23));
		listaAlumnos.add(new Alumno(44, "Oscar", "Dom�nguez", "Historia", 6.2, 24));
		listaAlumnos.add(new Alumno(45, "Alicia", "Prieto", "F�sica", 7.9, 18));
    
    
    
		// Ejercicio 1
		//a
//		listaAlumnos.forEach(alumno -> System.out.println(alumno));
		//b
//		listaAlumnos.forEach(System.out::println);
	
		
		// Ejercicio 2
		
//		listaAlumnos.stream()
//						.filter(e -> e != null)
//						.filter(e -> e.getEdad() > 18)
//						.forEach(alumno -> System.out.println(alumno.getNombres()));
						
		// Ejercicio 3
//		listaAlumnos.stream()
//					.filter(e -> e != null)
//					.filter(e -> e.getNombres().charAt(0) == 'L' || e.getNombres().charAt(0) == 'G' )
//					.forEach(System.out::println);    
  
		// Ejercicio 4
		
//		System.out.println(listaAlumnos.stream().count());
		
		// Ejercicio 5
		
//		listaAlumnos.stream()
//				.filter(e -> e != null)
//				.filter(e -> e.getNombreCurso() == "PHP" && e.getNota() > 9 )
//				.forEach(System.out::println);
		
		// Ejercicio 6
//		listaAlumnos.stream().limit(5).forEach(System.out::println);
		
		// Ejercicio 7
//		Optional<Alumno> min = listaAlumnos.stream().filter(e -> e != null)
//					.min(Comparator.comparing(Alumno::getEdad));
//		System.out.println(min);
//		
//		Optional<Alumno> max = listaAlumnos.stream().filter(e -> e != null)
//					.max(Comparator.comparing(Alumno::getEdad));
//		System.out.println(max);
    
		// Ejercicio 8
//		System.out.println(listaAlumnos.stream().findFirst());
		
		// Ejercicio 9
//		listaAlumnos.stream()
//				.filter(e -> e.getNombreCurso().charAt(e.getNombreCurso().length() -1 ) == 't')
//				.forEach(System.out::println);
				
		// Ejercicio 10
//		listaAlumnos.stream()
//					.filter(e -> e.getNombreCurso().contains("a"))
//					.forEach(System.out::println);
		
		
		// Ejercicio 11
		
//		listaAlumnos.stream()
//				.filter(e -> e.getNombres().length() > 10)
//				.forEach(System.out::println);
    
		// Ejercicio 12
		
//		System.out.println(
//			listaAlumnos.stream()
//				.filter(e -> e.getNombreCurso() == "PHP").count()
//		);
//		
		
		// Ejercicio 13
//		List<String> apellidos = listaAlumnos.stream()
//				.map(e -> e.getApellidos())
//				.collect(Collectors.toList());
//		
		
		// Ejercicio 14
		
		
    
    
    }
    
}
