package servidorsockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import poolDeConexiones.JDBCConnectionPooling;

public class ServidorConexionTCP extends Thread {

    private Socket socket;
    private BufferedReader entrada = null;
    private PrintWriter salida = null;
    private JDBCConnectionPooling poolConexiones;
    
    public ServidorConexionTCP(Socket socket, JDBCConnectionPooling poolConexiones) {
        this.socket = socket;
        this.poolConexiones = poolConexiones;
        
        try {
            // Establece canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getProductosPedido(int orderId) throws InterruptedException {
        // Establecer conexión
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            con = poolConexiones.getConnection(this);
            // Recuperar datos y enviar al cliente

            pstmt = con.prepareStatement(
                    "SELECT p.ProductName, o.unitPrice, o.quantity, "
                    + " (o.unitprice*o.quantity)*(1-o.discount) "
                    + "From Products P "
                    + "Inner Join OrderDetails o "
                    + " on o.productid = p.productid "
                    + " where o.orderid = ?");
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                salida.println(rs.getString("ProductName") + " " + 
                               rs.getDouble("UnitPrice") + " " + 
                               rs.getInt("Quantity") + " " + 
                               rs.getDouble(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally{
            if (pstmt != null) 
                try {
                    pstmt.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            if (con != null)
                poolConexiones.releaseConnection(con);
        }
    }

    @Override
    public void run() {
        String orderId = new String();
        try {
            orderId = entrada.readLine();
            getProductosPedido(Integer.valueOf(orderId));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                entrada.close();
                salida.close();
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
