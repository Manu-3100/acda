package poolDeConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import servidorsockets.ServidorConexionTCP;

public class JDBCConnectionPooling {
    private final ArrayList<Connection> conexionesDisponibles = new ArrayList();
    private LinkedList<ServidorConexionTCP> lista = new LinkedList();
    
    private int numeroConexiones;
    private final String url;
    private final String usuario;
    private final String password;
    
    public JDBCConnectionPooling(String url, String usuario, String password, int numeroConexiones) throws SQLException{
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        this.numeroConexiones = numeroConexiones;
        
        for (int i = 0; i < numeroConexiones; i++) {
            conexionesDisponibles.add(DriverManager.getConnection(url, usuario, password));
        }
    }
    
    public synchronized Connection getConnection(ServidorConexionTCP cliente) throws SQLException, InterruptedException{
        lista.add(cliente);
        // Si el cliente no se encuentra a la cabeza de la cola de espera o
        // si no hay conexiones disponibles, tiene que esperar
        while (lista.peek() != cliente || conexionesDisponibles.size() == 0)
            wait();
        
        lista.poll(); // El cliente sale de la lista porque se le asigna una conexión.
        Connection con = conexionesDisponibles.get(0);
        conexionesDisponibles.remove(con);
        return con;
    }
    
    public synchronized void releaseConnection(Connection conexion){
        conexionesDisponibles.add(conexion);
        // No se puede despertar a los clientes con una instrucción notify()
        // porque no hay garantías de que se despierte el cliente que está 
        // en la cabeza de la cola de espera. 
        notifyAll();
    }
   
}
