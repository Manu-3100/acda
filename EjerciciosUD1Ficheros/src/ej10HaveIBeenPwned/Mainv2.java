package ej10HaveIBeenPwned;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Mainv2 {

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			
			String passwd;
			int cont;
			
			while ((passwd = br.readLine()) != null) {
				cont = PasswordV2.getContadorRegistros(passwd);
				
				if	(cont == 0)
					System.out.println("La contraseña " + passwd + " no esta registrada");
				else
					System.out.println("La contraseña " + passwd + " esta registrada : " + cont );
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
