package capaNegocio;

import java.io.*;
import java.util.Scanner;

public class main {
	    public static void main(String[] args) {
	        try {
	            // Crear subprocesos para cada servicio usando ProcessBuilder
	            Process procesoPadron = new ProcessBuilder("java", "dispensador", "P").start();

	            // Establecer los flujos de entrada y salida para comunicarse con cada proceso
	            BufferedWriter writerPadron = new BufferedWriter(new OutputStreamWriter(procesoPadron.getOutputStream()));
	            BufferedReader readerPadron = new BufferedReader(new InputStreamReader(procesoPadron.getInputStream()));

	          // Lógica para recibir las solicitudes de los usuarios
	            Scanner scanner = new Scanner(System.in);

	            while (true) {
	                System.out.print("Ingrese el servicio deseado (P/R/S): ");
	                String input = scanner.nextLine().trim().toUpperCase();

	                if (input.equals("SALIR")) {
	                    break;
	                }

	                switch (input) {
	                    case "P":
	                        writerPadron.write("");  // Envía una solicitud al proceso Padrón
	                        writerPadron.newLine();
	                        writerPadron.flush();
	                        System.out.println(readerPadron.readLine());  // Lee el ticket generado
	                        break;

	                    default:
	                        System.out.println("Opción no válida. Por favor ingrese P, R o S.");
	                }
	            }

	            // Terminar procesos al finalizar
	            procesoPadron.destroy();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

