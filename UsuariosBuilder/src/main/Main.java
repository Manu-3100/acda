package main;

import CapaNegocioB.Usuario;

public class Main {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario.Builder()
							.email("Pedro@gmail.com")
							.nombre("Pedro")
							.apellidos("González")
							.telefono("666.66.66.66")
							.direccion("C\\ Rúa del Percebe, nº 9")
							.build();
		
		System.out.println(usuario);
	
	}

}
