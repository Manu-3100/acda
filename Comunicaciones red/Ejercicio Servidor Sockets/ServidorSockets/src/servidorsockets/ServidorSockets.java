package servidorsockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import poolDeConexiones.JDBCConnectionPooling;

public class ServidorSockets {

    public static final int PORT = 4444;
    
    public static void main(String[] args) {
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
        JDBCConnectionPooling poolConexiones = null;
        try {
            poolConexiones = 
                new JDBCConnectionPooling("jdbc:mysql://localhost/northwind", "root", "", 5);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
        
        ServidorConexionTCP conexion;
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            while (true) {
                conexion = new ServidorConexionTCP(socketServidor.accept(), poolConexiones);
                executor.execute(conexion);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
    
}
