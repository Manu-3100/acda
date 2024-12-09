package main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PriceListHandler extends DefaultHandler {
	
	private String nombreCafe;
	private double precio;
	
	private boolean inName;
	private boolean inMyCoffee;
	private boolean inPrice;
	
	
	
	public PriceListHandler(String nombreCafe) {
		this.nombreCafe = nombreCafe;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equals("name"))
			inName = true;
		else if (qName.equals("price") && inMyCoffee) {
			inPrice = true;
			inName = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String s = new String(ch, start, length);
		if(inName && s.equals(nombreCafe)) {
			inMyCoffee = true;
		} else if (inPrice){
			precio = Double.parseDouble(s);
			throw new StopParsingException("Café encontrado");
		}
		
			
	}
}
