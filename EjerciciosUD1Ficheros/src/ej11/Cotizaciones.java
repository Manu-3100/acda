package ej11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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

	private static final String FICHERO = "d:/pr2.txt";
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
						System.out.println(empresa + "  " + valores[0] + "  " + valores[1] + "  " + valores[2]);

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

	public static void grabarHistorico() {

		
		// el valor true indicado como segundo parámetro en el constructor 
		// de FileWriter indica que al crear la clase los nuevos datos no
		// se añaden al fichero, si no se indica nada o se indica false
		// se borrará el contenido y se escribirá el nuevo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO))) {

			String[] valores;

//			 
//			for(Entry<String, String[]> entry: map.entrySet()) {
//				String empresa = entry.getKey();
//				valores = map.get(empresa);
//			}
//			
//			// por cada clave su conjunto de valores
//			for(String empresa: map.keySet()) {
//				valores = map.get(empresa);
//				bw.write(valores[2] + "#" + valores[1] + "#" + valores + "#" + valores[0]);
//				bw.newLine();
//			}
			getMap().forEach((key, value) -> {
				try {
					bw.write(value[2] + "#" + 
							 value[1] + "#" +
							 key + "#" + 
							 value[0]);
					bw.newLine();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			});
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void leerHistorico(String empresa) {
		String linea;
		String nombre;
		String empresaFichero;
		String [] valores;
		String [] valoresMap = new String [3];
		
		try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {
			
			while ((linea = br.readLine()) != null ) {
				
				valores = linea.split("#");
				
				if(valores[2].equalsIgnoreCase(empresa)){
					// valores:
					// pos 0: Cotizacion
					// pos 1: Hora de cotización
					// pos 2: Fecha de cotización
					valoresMap[0] = valores[3];
					valoresMap[1] = valores[1];
					valoresMap[2] = valores[0];
					
					map.put(valores[2], valoresMap);
				}
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public static List<String> getCotizaciones() {
		List<String> lista = new ArrayList<String>();
		
		map.forEach((key, value) ->{
			lista.add(key + " " + value[0]);
		});
		
		
//		Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry<String, String[]> entrada = it.next();
//			lista.add(entrada.getKey() + " " + entrada.getValue()[0]);
//			
//		}
		
		return lista;
		
	}
	

}
