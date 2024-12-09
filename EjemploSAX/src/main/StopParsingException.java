package main;

import org.xml.sax.SAXException;

public class StopParsingException extends SAXException{

	public StopParsingException(String message) {
		super(message);
	}
}
