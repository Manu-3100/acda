package servidortcpecomultihilo;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServidorConexiónTCP extends Thread {

    Socket socket;
    BufferedReader entrada = null;
    PrintWriter salida = null;
    int idCliente;

    public ServidorConexiónTCP(Socket socket, int idCliente) {
        this.socket = socket;
        this.idCliente = idCliente;

        try {
            // Establece canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        String str = new String();
        try {
            do {
                str = entrada.readLine();
                str = new String(str.getBytes());
                System.out.println("Cliente " + idCliente + ": " + str);
                salida.println(str);

            } while (!str.equalsIgnoreCase("Adios"));
        } catch (IOException ex) {
            System.out.println("Conexión perdida con el cliente " + idCliente);
        }
    }
}

public class ServidorTCPEcoMultihilo {

    public static final int PORT = 4444;
    int idCliente = 0;

    public ServidorTCPEcoMultihilo() {
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", PORT);
            socketServidor.bind(addr);
        } catch (IOException e) {
            System.out.println("No se puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        System.out.println("Escuchando: " + socketServidor);

        ServidorConexiónTCP conexión;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            while (true) {
                conexión = new ServidorConexiónTCP(socketServidor.accept(), idCliente++);
                executor.execute(conexión);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        new ServidorTCPEcoMultihilo();
    }
}
