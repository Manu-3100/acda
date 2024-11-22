package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Main { 

	public static void main(String[] args) {
		String ruta = "d:/ferdebman/acda/pruebaGit";
		String linea;
		ArrayList<String> values = new ArrayList <String>();
		values.add("git");
		values.add("log");
		values.add("--pretty=format:\"%an;%ar;%s\"");
		values.add("-20");
		
		ProcessBuilder pb = new ProcessBuilder(values);
		pb.command();
		pb.directory(new File(ruta));

		
		try {
			Process p = pb.start();
			Stream<String> lineas = (new BufferedReader( new InputStreamReader(p.getInputStream(), Charset.forName("Windows-1252")))).lines();
				Object [][] datos = lineas
					.filter(l -> l.contains("ferdebman"))
					.map(l -> l.split(";")) // stream de vectores
					.toArray(Object[][] :: new);
							//size -> new String[size][3]
				String [] columnas = {"Autor", "Hace", "Mensaje"};
				JTable tabla = new JTable(datos, columnas);
				JFrame ventana = new JFrame("Datos sobre últimos commits");
				ventana.add(new JScrollPane(tabla));
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ventana.pack();
				ventana.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
