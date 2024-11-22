package main;

import java.io.File;
import java.rmi.UnmarshalException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import capaNegocio.*;

public class Marshall {

	public static void main(String[] args) {
		
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("capaNegocio");
			
			// hacemos un unmarshall para rellenar los objetos de la capa de negocio con datos.
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<Root> rootElement = (JAXBElement<Root>) unmarshaller.unmarshal(new File("xml/personas.xml"));
			
			// proceso Marshall: convertimos datos de objetos a XML
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			File output = new File("xml/nuevo.xml");
			marshaller.marshal(rootElement, output);
			
			System.out.println("Archivo XML generado en " + output.getAbsolutePath());
			
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		}

	}

}
