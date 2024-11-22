package ej10HaveIBeenPwned;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordV2 {

	public static String getSHA1(String contraseña) {
			
			String sha1 = "";
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-1");
				digest.reset();
				digest.update(contraseña.getBytes("utf8"));
				sha1 = String.format("%040x", new BigInteger(1, digest.digest())).toUpperCase();
			} catch (NoSuchAlgorithmException e) {
				System.out.println(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
			}
			return sha1;
		}
	
	public static int getContador(String sha1) {
		final String WEB = "https://api.pwnedpasswords.com/range/" + 
					sha1.substring(0, 5);
		final String TERMINACION = sha1.substring(5, 40);
		
		URL web;
		String [] info;
		String linea;
		
		try {
			web = new URL(WEB);
			HttpURLConnection con = (HttpURLConnection) web.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			while ((linea = br.readLine()) != null) {
				info = linea.split(":");
				
				if(info [0].equals(TERMINACION))
					return  Integer.parseInt(info[1]);
				
			}
			
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return 0;
		
	}
	
	public static int getContadorRegistros(String password) {
		// Busco alta cohesión en los métodos
		// intento evitar que el método getContador() tenga que
		// obtener el SHA-1 y consultar la web. Son dos tareas por lo
		// que la cohesión es baja y disminuye la reutilizacion del método
		return getContador(getSHA1(password));
		
	}

	
}
