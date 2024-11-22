package prueba;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import capaNegocio.Empleado;

public class Main {

	public static void main(String[] args) {
		
		Empleado e1 = new Empleado(1, "Jeff Bezos", 100_000);
		
		Empleado[] empleados = {	
			new Empleado(1, "Jeff Bezos", 100_000),
			new Empleado(2, "Bill Gates", 200_000),
			new Empleado(3, "Mark Zuckerberg", 300_000)
		};
		
//		Stream.Builder<Empleado> empleadoStreambuilder = Stream.builder();
		
//		Stream <Empleado> stream = Stream.of(empleados);
//		stream.map(e ->  e.mayusculas()).forEach(System.out::println);
		

//		
//		List<Empleado> lista = stream
//									.map(e -> e.mayusculas())
//									.collect(Collectors.toList());
//		for(Empleado e : lista)
//			System.out.println(e);
		
//		List <String> alpha = Arrays.asList("a","b","c","d");
//		List<String> lista = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
//		
//		lista.forEach(System.out::println);
		
		
		List<Empleado> lista = Arrays.asList(empleados);
		
		List<Empleado> nueva = lista.stream()
			.peek(e -> e.incrementarSalario(0.2))
			.peek(System.out::println)
			.collect(Collectors.toList());
			
		nueva.forEach(System.out::println);
			
			
			
	}
}