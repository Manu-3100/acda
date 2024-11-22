package capaNegocio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;

public class LogAnalyzerFactory {
	// metodo Factory
	public static LogAnalyzer getAnalizador (String file) throws Exception {
		String linea = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			linea = br.readLine();
			if (linea.startsWith("[ERROR]"))
				return new ErrorLogAnalyzer(file);
			else if (linea.startsWith("[USER]"))
				return new UserActivityLogAnalyzer(file);	
			else if (linea.startsWith("[PERFORMANCE]")) 
				return new PerformanceLogAnalyzer(file);	
			else 
				throw new InvalidParameterException("No se reconoce el tipo de ficheros");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
