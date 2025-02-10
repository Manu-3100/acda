package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocket {

    public static void main(String[] args) {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        BufferedReader stdIn = null;
        
        try {
            InetSocketAddress addr = new InetSocketAddress("localhost", 4444);
            socketCliente = new Socket();
            socketCliente.connect(addr);

            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream(), "UTF-8"));
            salida = new PrintWriter(socketCliente.getOutputStream());
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String linea;

            System.out.print("Código pedido: ");
            linea = stdIn.readLine();
            salida.println(linea);
            salida.flush();
            while ((linea=entrada.readLine()) != null){
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("No puede establecer canales de E/S para la conexión");
            System.err.println(e.getMessage());
            System.exit(-1);
        } finally {
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
