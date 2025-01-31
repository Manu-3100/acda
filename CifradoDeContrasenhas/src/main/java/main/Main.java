package main;

import java.util.Scanner;

import capaNegocio.Sal;
import capaNegocio.Usuario;

public class Main {

	static Scanner in = new Scanner(System.in);
	
	private static int menu() {
		System.out.println("-------------------");
		System.out.println("1. Nuevo Usuario ");
		System.out.println("2. Validar Usuario ");
		System.out.println("3. Salir ");
		System.out.println("-------------------");
		System.out.print("Opción: ");
		int opc = in.nextInt();
		in.nextLine(); // Vaciar enter
		
		return opc;
	}
	private static void alta() {
		System.out.print("Nombre: ");
		String nombre = in.nextLine();
		System.out.print("Contraseña: ");
		String contraseña = in.nextLine();
		Usuario usuario = Usuario.add(nombre, contraseña);
		if (usuario != null) {
			System.out.println(usuario);
		} else {
			System.out.println("No se pudo registrar el usuario.");
		}
	}
	private static void validar() {
		System.out.print("Nombre: ");
		String nombre = in.nextLine();
		System.out.print("Contraseña: ");
		String contraseña = in.nextLine();
		Usuario usuario = Usuario.validar(nombre, contraseña);
		if (usuario != null) {
			System.out.println(usuario);
		} else {
			System.out.println("No se pudo validar el usuario.");
		}
	}
	
	public static void main(String[] args) {
		int opc = 0;	
		do {
			opc = menu();
			switch (opc) {
				case 1: 
					alta();
					break;
				case 2:
					validar();
					break;
				default:
					System.out.println("Opción erronea");
			
			}
		
		} while(!(opc == 3));
		
		
	}
	
	
	
	
}
