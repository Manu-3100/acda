package main;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		PriceListHandler manejador = new PriceListHandler("Mocha Java");
		
		try {	
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse("target/priceList.xml", manejador);
		
		} catch (StopParsingException e) {
			System.out.println(manejador.getPrecio());
			
		} catch (SAXException e) {
			System.err.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}	
	}
}
