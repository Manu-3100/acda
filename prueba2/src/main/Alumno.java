package main;

import java.io.Serializable;

public class Alumno implements Serializable {
	
	private String nombre;
	private String apellidos;
	
	public Alumno(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alumno ->  nombre: ")
			   .append(nombre)
			   .append(" apellido: ")
			   .append(apellidos);
		return builder.toString();
	}
	
	
	

}
