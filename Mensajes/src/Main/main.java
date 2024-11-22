package Main;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			
			String nombre;
			
			while (!(nombre = in.nextLine()).equals("fin")) {
				System.out.println("Hola, " + nombre);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}