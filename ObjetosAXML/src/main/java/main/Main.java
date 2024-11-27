package main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) {
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("capaNegocio");
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty("jaxb.formatted,output", true);
			File output = new File("xml/nuevo.xml");
			
			Persona persona = new Persona();
			persona.setId(0);
			persona.setNombre("Alicia");
			persona.setApellido("González");
			DatatypeFactory factory = DatatypeFactory.newInstance();
			persona.setFechaNacimiento(factory.newXMLGregorianCalendar("2024-11-20"));
			
			Negocio negocio = new Negocio();
			negocio.setCodigo(0);
			negocio.setNombre("Alphabet");
			
			ListaPersona personas = new ListaPersona();
			personas.getPersona().add(persona);
			
			ListaNegocio negocios = new ListaNegocio();
			negocios.getNegocio().add(negocio);
			
			Root root = new Root();
			root.setListaPersona(personas);
			root.setListaNegocio(negocios);
			marshaller.marshal(root, output);
			System.out.println("Archivo XML generado en " + output.getAbsolutePath());
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		} catch (DatatypeConfigurationException e) {
			System.err.println(e.getMessage());
		}
	}
}
