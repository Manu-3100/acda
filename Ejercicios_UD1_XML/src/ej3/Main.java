package ej3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class Main {

	public static void main(String[] args) {

		Map<String, Noticia[]> map = new HashMap<String , Noticia[]>();
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		Handler handler = new Handler();
		
		
		final String XML = "https://www.microsiervos.com/index.xml" ;
		
		try {	
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(XML, handler);
			
			System.out.println("Noticias");
			List<Noticia> noticias = handler.getNoticias();
			if(noticias != null)
				noticias.forEach(System.out::println);
			

			System.err.println("\nb) Noticias por categoria");
			Map<String, List<Noticia>> categorias = handler.getCategorias();
			
			for(String categoria : categorias.keySet()) {
				
				System.out.println(categoria);
				categorias.get(categoria).forEach(System.out::println);
				
			}
			
		} catch (SAXException | ParserConfigurationException | IOException e) {
			System.err.println(e.getMessage());
		}	
		
	}
}
