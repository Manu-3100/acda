package main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import capaNegocio.Persona;
import capaNegocio.Personas;

public class Main2 {

	private static String CAPA_NEGOCIO = "capaNegocio";
	private static String FICHERO_PERSONAS = "xml/personas.xml";

	private static Personas unmarshal() {
		Personas personas = null;
		try {
			JAXBContext context = JAXBContext.newInstance(CAPA_NEGOCIO);
			Unmarshaller rellenarObjetos = context.createUnmarshaller();
			personas = (Personas) rellenarObjetos.unmarshal(new File(FICHERO_PERSONAS));
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		}
		return personas;
	}
	
	private static void marshal(Personas personas) {
		try {
			JAXBContext context = JAXBContext.newInstance(CAPA_NEGOCIO);
			Marshaller rellenarObjetos = context.createMarshaller();
			rellenarObjetos.marshal(personas ,new File("xml/nuevo.xml"));
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void update(Personas personas, String nombre, Persona persona) {
		for (Persona p : personas.getPersona()) {
			if (p.getNombre().equals(nombre)) {
				p.setDocumento(persona.getDocumento());
				p.setApellidos(persona.getApellidos());
				p.setApellidos(persona.getApellidos());
				break;
			}
		}
	}
	
	private static void delete (Personas personas, Persona persona) {
		for (Persona p : personas.getPersona()) {
			if (p.getNombre().equals(persona.getNombre())) {
				personas.getPersona().remove(persona);
			}
		}
	}
		
	private static void add(Personas personas, Persona persona) {
		personas.getPersona().add(persona);
	}
	
	private static void mostrar(Personas personas) {
		System.out.println("-------------------");
		personas.getPersona().forEach(System.out::println);
		System.out.println("-------------------");
	}


	public static void main(String[] args) {
		
		Personas personas = unmarshal();

		mostrar(personas);
		
		Persona humano = new Persona();
		humano.setNombre("Nadir");
		humano.setApellidos("Ibn");
		humano.setDocumento(43265);
		
		update(personas,"Silvester", humano);
		
		Persona humano2 = new Persona();
		humano2.setNombre("Manuel");
		humano2.setApellidos("Fernández");
		humano2.setDocumento(43265);
		add(personas, humano2);
		
		for(Persona pers: personas.getPersona()) {
			if(pers.getNombre().equals("Pepe") &&
				pers.getApellidos().equals("Grillo")	) {
				delete(personas, pers);
				break;
			}
		}
		
		marshal(personas);
		
		mostrar(personas);
	}
}
