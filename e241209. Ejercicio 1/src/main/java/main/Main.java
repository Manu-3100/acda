package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import capaNegocio.*;

public class Main {

	private final static String FABRICA = "target/fabrica.json";
	private final static String PEDIDOS = "target/pedidos.txt";
	private final static String OUTPUT = "target/output.txt";
	
	// Ejercicio 1.a)		1 punto
	private static Inventario leerJson() {
		// Leer el fichero FABRICA y volcarlo a la clase Inventario.
		// Inventario deberá contener las clases necesarias para almacenar
		// el contenido especificado en el JSON.
		ObjectMapper om = new ObjectMapper();
		Inventario inventario = null;
		try {
			byte[] jsonData = Files.readAllBytes(Paths.get(FABRICA));
			inventario = om.readValue(jsonData, Inventario.class);
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return inventario;
	}
	
	// Ejercicio 1.b)		1 punto
	private static List<Pedido> LeerPedidos() {
		// Leer el fichero PEDIDOS y para cada línea, crear un Pedido y 
		// almacenarlo en un List<Pedido>. 
		// Ordenar los pedidos porque deben ordenarse de modo creciente
		// por fecha. Se hace con la siguiente instrucción. Los pedidos 
		// saben cómo ordenarse porque implementan la interfaz Comparable<Pedido>.
		List<Pedido> res = new ArrayList<Pedido>();
		Pedido pedido;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(PEDIDOS)))) {
			String linea;
			int idCliente;
			int idProducto;
			int cantidad;
			LocalDate fecha;
			while ((linea = br.readLine()) != null) {
				idCliente =  Integer.parseInt(linea.split(":")[0]);
				idProducto = Integer.parseInt(linea.split(":")[1]);
				cantidad = Integer.parseInt(linea.split(":")[2]);
				fecha = LocalDate.parse(linea.split(":")[3]);
				pedido = new Pedido( idCliente, idProducto, cantidad, fecha);
				res.add(pedido);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		Collections.sort(res);
		
		return res;
	}
	
	// Ejercicio 1.d)		1 punto
	public static void main(String[] args) {
		// Leer inventario del JSON.
		Inventario inventario = leerJson();
		// Leer pedidos del fichero de texto plano.
		List<Pedido> pedidos = LeerPedidos();
		
		// Para cada pedido intentar tramitarlo y grabar el resultado en OUTPUT.
		try (BufferedWriter bw = new BufferedWriter( new FileWriter(OUTPUT, true))) {
			
			for(Pedido pedido : pedidos) {
				
				if (pedido.tramitar(inventario)) {
					bw.write(pedido.toString() + "A sido tramitado");
					bw.newLine();
				}
				else {
					bw.write(pedido.toString() + "No se ha tramitado");
					bw.newLine();
				}
			}
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
