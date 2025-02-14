package servidorudpeco;

import java.io.IOException;
import java.net.*;

public class ServidorUDPEco {

    public static void main(String argv[]) {
        DatagramSocket datagramSocket = null;
        try {
            // Creamos el socket del servidor
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            datagramSocket = new DatagramSocket(addr);
            
            // Establecemos un timeout de 30 segs
            datagramSocket.setSoTimeout(30000);
            
            DatagramPacket datagramaRec, datagramaEnv;
            String mensaje;
            
            do {
                // Preparamos un datagrama para recepción
                byte[] array = new byte[1024];
                datagramaRec = new DatagramPacket(array, array.length);
                
                // Recibimos el mensaje
                datagramSocket.receive(datagramaRec);
                mensaje = (new String (array)).trim();
                System.out.println("Cliente: " + mensaje + 
                        ". Recibido de " + datagramaRec.getAddress().toString() + 
                        ":" + datagramaRec.getPort());
                
                // Preparamos el datagrama que vamos a enviar
                datagramaEnv = new DatagramPacket(array, array.length,
                    datagramaRec.getAddress(), datagramaRec.getPort());
                
                // Enviamos el mensaje
                datagramSocket.send(datagramaEnv);
                
            } while (!mensaje.trim().equalsIgnoreCase("Adios"));
            System.out.println("Proceso finalizado");
        } catch (SocketTimeoutException e) {
            System.err.println("30 segs sin recibir nada");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerramos el socket
            datagramSocket.close();
        }
    }
}
