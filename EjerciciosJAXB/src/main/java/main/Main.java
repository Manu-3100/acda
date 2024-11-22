package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import capaNegocio.*;

public class Main {

	public static void main(String[] args) {

		try {
			JAXBContext context = JAXBContext.newInstance("capaNegocio");
			Unmarshaller rellenarObjetos = context.createUnmarshaller();
			Personas personas = (Personas) rellenarObjetos.unmarshal(new File("xml/personas.xml"));
			
			for(Persona persona: personas.getPersona()) {
				if(persona.getNombre().equals("Silvester")) {
					persona.setNombre("Rambo");
				}
			}
			
			Persona humano = new Persona();
			humano.setNombre("Nadir");
			humano.setApellidos("Ibn");
			humano.setDocumento(43265);
			
			personas.getPersona().add(humano);
			
			for(Persona persona: personas.getPersona()) {
				if (persona.getNombre().equals("Pepe")) {
					personas.getPersona().remove(persona);
				}
			}
			
			Marshaller iterarObjetos = context.createMarshaller();
			iterarObjetos.setProperty("jaxb.formatted.output", true);
			iterarObjetos.marshal(personas, new File("xml/nuevo.xml"));
			
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		}
		
		
	}

}
