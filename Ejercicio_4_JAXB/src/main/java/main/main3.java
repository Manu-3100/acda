package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class main3 {
	public static void main(String[] args) {
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		
		boolean coffeEncontrado = false; 
		
		try (InputStream inputStream = new FileInputStream("G:\\2DAM\\acda\\EjemploSAX\\target\\priceList.xml")) {
			reader = factory.createXMLStreamReader(inputStream);
			String nombre = null;
			double precio = 0;
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					String tagName = reader.getLocalName();
					if (tagName.equals("name")) {
						nombre = reader.getElementText();
						if(nombre.equals("Mocha Java")) 
							coffeEncontrado = true;
						else 
							coffeEncontrado = false;
					}
					else if (tagName.equals("price") && coffeEncontrado)
						precio = Double.parseDouble(reader.getElementText());
					break;
				case XMLStreamConstants.END_ELEMENT:
					if (coffeEncontrado) {
						System.out.println("Café:");
						System.out.println("Nombre: " + nombre);
						System.out.println("Precio: " + precio);
						System.out.println("-----------");
					}
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo XML no encontrado: " + e.getMessage());
		} catch (XMLStreamException e) {
			System.out.println("Error al procesar el XML: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al procesar el XML: " + e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (XMLStreamException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}