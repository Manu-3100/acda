package clientetcpeco;

import java.net.*;
import java.io.*;

public class ClienteTCPEco {

    public static void main(String[] args) throws IOException {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        BufferedReader stdIn = null;
        
        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que está en la misma máquina que el cliente
        // y que escucha en el puerto 4444
        try {
            //InetSocketAddress addr = new InetSocketAddress("localhost", 4444);
            //socketCliente = new Socket();
            //socketCliente.connect(addr);
            InetSocketAddress addr = new InetSocketAddress("localhost", 4444);
            socketCliente = new Socket();
            socketCliente.connect(addr);
            // Obtenemos el canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream(), "UTF-8"));
            // Obtenemos el canal de salida
//            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream(), "UTF-8")), true);
            salida = new PrintWriter(socketCliente.getOutputStream());
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String linea;

            // El programa cliente no analiza los mensajes enviados por el
            // usario, simplemente los reenvía al servidor hasta que este
            // se despide con "Adios"
            do {
                // Leo la entrada del usuario
                System.out.print("Entrada: ");
                linea = stdIn.readLine();
                // La envia al servidor
                salida.println(linea);
                salida.flush();
                // Envía a la salida estándar la respuesta del servidor
                linea = entrada.readLine();
                System.out.println("Respuesta servidor: " + linea);
                // Si es "Adios" es que finaliza la comunicación
            } while (!linea.equalsIgnoreCase("Adios"));
        } catch (IOException e) {
            System.err.println("No puede establecer canales de E/S para la conexión");
            System.err.println(e.getMessage());
            System.exit(-1);
        } finally {
            // Libera recursos
            try {
                salida.close();
                entrada.close();
                stdIn.close();
                socketCliente.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
