package ej8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) {

		String file = "d:/pr1.txt";
		String linea;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
