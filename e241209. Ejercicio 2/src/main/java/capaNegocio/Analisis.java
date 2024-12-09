package capaNegocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.SAXException;

public class Analisis {

	// Ejercicio 2.1 1 punto
	/**
	 * Usando JAXB, leer el fichero pom y obtener las dependencias especificadas.
	 * 
	 * @param ficheroPom
	 * @return Dependencias especificadas en el fichero pom.
	 */
	private List<Dependency> getDependencias(String ficheroPom) {

		List<Dependency> ListaDependencias = new ArrayList<Dependency>();
		try {
			JAXBContext contexto = JAXBContext.newInstance("capaNegocio");
			Unmarshaller unmarshaller = contexto.createUnmarshaller();
			Project datos = (Project) unmarshaller.unmarshal(new File(ficheroPom));

			for (Dependency dependencia : datos.getDependencies().getDependency()) {
				ListaDependencias.add(dependencia);
			}
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
		}
		return ListaDependencias;
	}

	// Ejercicio 2.2 1,5 puntos
	/**
	 * A partir del fichero xml de vulnerabilidades, se devuelven en formato
	 * lista<Library>
	 * 
	 * @param vulnerabilidades
	 * @return lista de instancias Library con las vulverabilidades especificadas en
	 *         el fichero xml.
	 */
	private List<Library> getLibrary(String fileVulnerabilidades) {

		List<Library> ListaVulnerabilidades = new ArrayList<Library>();
		Library vulneabilidad = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader eventReader = null;
		try {
			eventReader = factory.createXMLEventReader(new FileInputStream(fileVulnerabilidades));
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					String tagName = startElement.getName().getLocalPart();
					// cuando el tagName sea name
					if (tagName.equals("name")) {
						vulneabilidad = new Library();
						event = eventReader.nextEvent();
						if (event instanceof Characters) {
							// añado el nombre al objeto vulnerabilidad que cree
							vulneabilidad.setNombre(event.asCharacters().getData());
						}
						// cuando el tagName sea version
					} else if (tagName.equals("version")) {
						event = eventReader.nextEvent();
						if (event instanceof Characters) {
							// añado la vesion al objeto vulnerabilidad que cree
							vulneabilidad.setVersion(event.asCharacters().getData());
						}
					}
				}
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					// cuando la etiqueta de cierre indique que ya se cierra la etiqueta library
					// podemos añadir el elemento a la lista
					if (endElement.getName().getLocalPart().equals("library")) {
						ListaVulnerabilidades.add(vulneabilidad);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo XML no encontrado: " + e.getMessage());
		} catch (XMLStreamException e) {
			System.out.println("Error al procesar el XML: " + e.getMessage());
		} finally {
			if (eventReader != null)
				try {
					eventReader.close();
				} catch (XMLStreamException e) {
					System.out.println(e.getMessage());
				}
		}
		return ListaVulnerabilidades;
	}

	// Ejercicio 2.3 1 punto
	/**
	 * A partir de las dependencias de la aplicación y de las librerías que
	 * contienen las vulnerabilidades se indica si la aplicación es vulnerable o no.
	 * 
	 * @param dependencia Dependencias de la aplicación.
	 * @param librerias   Librerías que presentan vulnerabilidades.
	 * @return indicativo de si la aplicación es vulnerable.
	 */
	private boolean esVulnerable(Dependency dependencia, List<Library> vulnerabilidades) {

		for (Library vulnerabilidad : vulnerabilidades) {
			if (vulnerabilidad.getNombre().equals(dependencia.getArtifactId())
					&& vulnerabilidad.getVersion().equals(dependencia.getVersion())) {
				return true;
			}
		}
		return false;
	}

	// Ejercicio 2.4 1,5 puntos
	/**
	 * A partir de las dependencias de la aplicación y de las vulnerabilidades,
	 * cuando una dependencia presenta una vulnerabilidad, se añade a un mapa. Dado
	 * que una dependencia puede tener más de una vulnerabilidad, se registran en
	 * una lista.
	 * 
	 * @param librerias
	 * @param dependencias
	 * @return Mapa con las dependencias y una lista de sus vulnerabilidades.
	 */
	private HashMap<String, List<String>> getVulnerabilidades(List<Dependency> dependencias,
			List<Library> vulnerabilidades) {

		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> versiones = null;

		for (Dependency dependencia : dependencias) {

			if (esVulnerable(dependencia, vulnerabilidades)) {
				if (map.containsKey(dependencia.getArtifactId())) {
					map.get(dependencia.getArtifactId()).add(dependencia.getVersion());
				} else {
					versiones = new ArrayList<String>();
					versiones.add(dependencia.getVersion());
					map.put(dependencia.getArtifactId(), versiones);
				}
			}

		}
		return map;
	}

	public Map<String, List<String>> verificar(String ficheroPom, String vulnerabilidades) {
		List<Dependency> dependencias = getDependencias(ficheroPom);
		List<Library> libraries = getLibrary(vulnerabilidades);

		return getVulnerabilidades(dependencias, libraries);
	}
}
