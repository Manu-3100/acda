package main;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import capaNegocio.*;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance("capaNegocio").createUnmarshaller();
			JAXBElement<Root> rootElement = (JAXBElement<Root>) unmarshaller.unmarshal(new File("xml/personas.xml"));
			
			Root root = rootElement.getValue();
			
			for (Persona persona: root.getListaPersona().getPersona()) {
				System.out.println(persona);
			}
			
			for(Negocio negocio: root.getListaNegocio().getNegocio()) {
				System.out.println(negocio);
			}
			
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
