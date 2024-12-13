package generico;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore.SecretKeyEntry;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {
	
	private static final String ALGORITHM = "AES";
	
	public static SecretKey generatekey() throws NoSuchAlgorithmException {
		
		// creamos la instancia con el algoritmo deseado
		KeyGenerator keyGen =  KeyGenerator.getInstance(ALGORITHM);
		// le decimos con cuantos bits vamos a trabajar
		keyGen.init(256);
		// generamos la key y la devolvemos
		return keyGen.generateKey();		
	}
	
	// pasar la key a cadena de texto
	public static String getSecretKeyAsText(SecretKey secretKey) {
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
	
	public static void grabarKey(SecretKey secretKey) {
		try (FileOutputStream fos = new FileOutputStream("target/key")) {
			
			fos.write(secretKey.getEncoded());
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static SecretKey leerKey() {
		SecretKey key = null;
		try {
			
			byte[] keyBytes = Files.readAllBytes(Paths.get("target/key"));
			key = new SecretKeySpec(keyBytes, "AES");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return (key == null)? null: key;
	}
	
}
