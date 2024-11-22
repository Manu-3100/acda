package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ej6 {
	public static void main(String[] args) {
		int contador;

		if (args.length == 0) {
			System.out.println("Debe proporcionar, al menos, un nombre de fichero.");

		}
		for (int i = 0; i < args.length; i++) {
			try (BufferedReader br = new BufferedReader(new FileReader(args[i]))) {
				contador = 0;
				while (br.readLine() != null) {
					contador++;
					System.out.println(args[i] + " " + contador);
				}
				
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
