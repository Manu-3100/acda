package ej10HaveIBeenPwned;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		try (BufferedReader bf = new BufferedReader(new FileReader(args[0]))) {
			String linea;
			String passwdHash;
			String url;
			while ((linea = bf.readLine()) != null) {
				url = "https://api.pwnedpasswords.com/range/";
				url += Password.getSHA1(linea).substring(0, 5);
				passwdHash = Password.getSHA1(linea);
								
				if(Password.aparece(Password.getInfoApi(url), Password.getSHA1(linea))) {
					System.out.println(linea + "  " + "te jodes");
				}else {
					System.out.println(linea + "  " + "Alegrate");
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
