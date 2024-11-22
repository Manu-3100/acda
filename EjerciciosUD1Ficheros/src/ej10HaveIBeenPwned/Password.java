package ej10HaveIBeenPwned;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Password {

	public static String getSHA1(String contraseña) {
		
		String hash = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(contraseña.getBytes("utf8"));
			hash = String.format("%040x", new BigInteger(1, digest.digest())).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return hash;
	}
	
	public static List<String> getInfoApi(String url) {
		List<String> lista = new ArrayList<String>();
		
		URL web;
		try {
			web = new URL(url);
			HttpURLConnection con = (HttpURLConnection) web.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			lista = br.lines().collect(Collectors.toList());
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public static boolean aparece(List<String> lista, String password ) {
		String contenido;
		for(String linea : lista) {
			contenido = password.substring(5, password.length());
			if (linea.split(":")[0].equals(contenido)) {		
				return true;
			}
		}
		return false;
	}	
}