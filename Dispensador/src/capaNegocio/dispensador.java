package capaNegocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class dispensador {

	private static int nextTicket = 1; // Contador de tickets para el servicio

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Especifica el código del servicio (P, R, S).");
			System.exit(1);
		}

		String codigoServicio = args[0];

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

			while (true) {
				// Espera recibir una línea como solicitud de ticket
				reader.readLine();

				// Genera el ticket y envía la respuesta al proceso principal
				writer.write(codigoServicio + nextTicket);
				writer.newLine();
				writer.flush();

				nextTicket++; // Incrementa el contador del ticket
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
