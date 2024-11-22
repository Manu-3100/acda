package Main;

import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// Crear una instancia de ProcessBuilder con el comando que queremos ejecutar
		ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");

		try {
			// Iniciar el proceso
			Process process = processBuilder.start();

			// Esperar a que el proceso termine (opcional)
			int exitCode = process.waitFor();
			System.out.println("El Bloc de notas terminó con el código: " + exitCode);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
