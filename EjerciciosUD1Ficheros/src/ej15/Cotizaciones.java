package ej15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Cotizaciones {

	// clave: Nombre cotización
	// valores:
	// pos 0: Cotizacion
	// pos 1: Hora de cotización
	// pos 2: Fecha de cotización
	private static Map<String, String[]> map = new HashMap<String, String[]>();

	public static Map<String, String[]> getMap() {
		return map;
	}

	private static final String COTIZACIONES = "D:\\ferdebman\\archivos\\cotizaciones.dat";
	private static final String INDICES = "D:\\ferdebman\\archivos\\indices.txt";
	private static final String PAGINA = "https://www.expansion.com/mercados/cotizaciones/indices/ibex35_I.IB.html";

	public static void leerUltimas() {

		String linea;
		String empresa;
		String temp;
		String[] valores;

		URL url;
		try {
			// URL no implementa la interfaz AutoCloseable por lo tanto
			// no se puede utilizar dentro de un try-with-resources.
			url = new URL(PAGINA);

			try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
				while ((linea = br.readLine()) != null) {
					if (linea.contains("<td class=\"primera\"")) {
						valores = new String[3];

						empresa = linea.split("title=\"")[1].split("\"")[0];

						// Contenido línea : <td>valor</td>
						valores[0] = (linea = br.readLine()).substring(4, linea.length() - 5);

						// 7 líneas con contenido que no nos interesa
						for (int i = 0; i < 7; i++)
							br.readLine();

						// Contenido línea : <td>cotizacion</td>
						valores[1] = (linea = br.readLine()).substring(4, linea.length() - 5);

						// se obtiene la fecha del sistema ya que no figura en la web
						valores[2] = LocalDate.now().toString();

						map.put(empresa, valores);
					}
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void grabar() {
		try(RandomAccessFile raf = new RandomAccessFile(COTIZACIONES, "rw");
				FileWriter fw = new FileWriter(INDICES, true)){
			
			// se añade a los ficheros todo el contenido del HashMap
			raf.seek(raf.length()); // nos posicionamos al final del fichero para añadir cotizaciones
			NumberFormat nf = NumberFormat.getInstance();
			double cotizacion;
			for(String key: map.keySet()) {
				//fecha#hora#empresa#posición
				fw.write(map.get(key)[2] + "#" + map.get(key)[1] + "#" + key + "#" + raf.getFilePointer() + "\n");
			
				// Double.parseDouble(map.get(key)[0].replace(',', '.'));
				cotizacion = nf.parse(map.get(key)[0]).doubleValue();
				
				raf.writeDouble(cotizacion);
				
			}
			
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public List<String> leer(String dia) {
		
		List<String> lista = new ArrayList<String>();
		String linea;
		String [] valores;
		try (BufferedReader br = new BufferedReader(new FileReader(INDICES));
				RandomAccessFile raf = new RandomAccessFile(COTIZACIONES, "r") ) {	
			
			//fecha#hora#empresa#desplazamiento
			while ((linea=br.readLine()) != null) {
				if(linea.startsWith(dia)) {
					valores = linea.split("#");
					raf.seek(Long.parseLong(valores[3]));
					
					lista.add("Empresa: " + valores[2] +
							  " \tCotizacion " + raf.readDouble());
				}
			}	
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		
		return lista;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
