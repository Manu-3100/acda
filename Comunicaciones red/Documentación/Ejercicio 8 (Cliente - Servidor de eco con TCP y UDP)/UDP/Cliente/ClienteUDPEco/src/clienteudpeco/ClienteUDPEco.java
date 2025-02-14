package clienteudpeco;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteUDPEco {

    public static void main(String argv[]) {

        Scanner teclado = new Scanner(System.in);
        DatagramSocket sDatagram = null;

        try {
            // Creamos el socket no orientado a conexión
            // (en cualquier puerto libre)
            sDatagram = new DatagramSocket();
            // Establecemos un timeout de 30 segs
            sDatagram.setSoTimeout(30000);
            // Obtenemos la dirección IP del servidor
            InetAddress dirServidor = InetAddress.getByName("localhost");
            String mensaje;
            
            do {
                System.out.print("Mensaje: ");
                mensaje = teclado.nextLine();

                // --------- Envío de un datagrama al servidor ------------ 
                // Preparamos el datagrama que vamos a enviar y lo enviamos
                DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(),
                        mensaje.getBytes().length, dirServidor, 5555);

                // Enviamos el datagrama
                sDatagram.send(datagrama);

                System.out.println("CLIENTE: Enviando "
                        + new String(datagrama.getData()) + " a "
                        + datagrama.getAddress().toString() + ":"
                        + datagrama.getPort());

                // --------- Respuesta del servidor de Eco UDP ----------
                // Preparamos el datagrama de recepción
                byte array[] = new byte[1024];
                DatagramPacket dgramRec = new DatagramPacket(array, array.length);
                // Recibimos el mensaje
                sDatagram.receive(dgramRec);

                System.out.println("CLIENTE: Recibido "
                        + new String(dgramRec.getData(), 0, dgramRec.getLength())
                        + " de " + dgramRec.getAddress().toString() + ":"
                        + dgramRec.getPort());
                
            } while (!mensaje.equalsIgnoreCase("Adios"));
        } catch (SocketTimeoutException e) {
            System.err.println("30 segs sin recibir nada");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Cerramos el socket para liberar la conexión
            sDatagram.close();
        }
    }
}
