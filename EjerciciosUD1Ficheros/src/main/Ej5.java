package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ej5 {
	public static void main(String[] args) {
		
		int contador = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
			
			while (br.readLine() != null) {
				contador++;
				System.out.println(contador);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
