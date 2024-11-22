package ej10HaveIBeenPwned;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordV3 {

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
		final String WEB = "https://api.pwnedpasswords.com/range/" + sha1.substring(0, 5);
		final String TERMINACION = sha1.substring(5, 40);

		URL web;
		String[] info;
		String linea;

		try {
			// Patrón factory method
			HttpClient client = HttpClient.newHttpClient();
			// Patrón builder
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(WEB))
					.build();
			
			// el método ofString devuelve una única cadena 
			// con toda la respuesta de la web
			// no parece muy fácil de procesar
			// el método ofLines devuelve un Stream de Strings
			HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());
		
			
			// para procesar el Stream lo comvierto a List
			List<String> lista = response.body().collect(Collectors.toList());
			
			for(String l : lista) {
				
				info = l.split(":");
				if(info [0].equals(TERMINACION))
					return  Integer.parseInt(info[1]);
			}
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch(InterruptedException e) {
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
