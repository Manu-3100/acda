package ej3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {
	
	
	private List<Noticia> noticias = new ArrayList<Noticia>();
	
	private String texto;
	private Noticia noticia;
	boolean inItem = false;
	
	private static DateTimeFormatter formato = DateTimeFormatter.RFC_1123_DATE_TIME;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equals("item")) {
			noticia = new Noticia();
			inItem = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		switch (qName) {
		case "title":
			if(inItem)
			noticia.setTitulo(texto);
			break;
			case "author":
				noticia.setAutor(texto);
				break;
			case "category":
				noticia.setCategoria(texto);
				break;
			case "pubDate":
				LocalDateTime fechaHora = LocalDateTime.parse(texto, formato);
				noticia.setPubFecha(fechaHora);
				break;
			case "item":
				noticias.add(noticia);
				inItem = false;
				break;
		}
	
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		if(inItem)
			texto = new String(ch, start, length);
	}
	
	public List<Noticia> getNoticias() {
		
		if (noticias.isEmpty())
			return null;
		
		return noticias;
	}
	
	public Map<String, List<Noticia>> getCategorias(){
		Map<String, List<Noticia>> map = new HashMap< String, List<Noticia>>();
		String categoria;
		List<Noticia> noticiasCategoria;
		for(Noticia noticia: noticias) {
			categoria = noticia.getCategoria();
			
			if (map.containsKey(categoria)) {
				noticiasCategoria = map.get(categoria);
				noticiasCategoria.add(noticia);
			} else {
				noticiasCategoria = new ArrayList<Noticia>();
				noticiasCategoria.add(noticia);
				map.put(categoria, noticiasCategoria);
			}
		}
		return map;
	}
	
}
