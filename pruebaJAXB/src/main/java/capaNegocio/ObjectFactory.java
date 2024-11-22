//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.14 a las 01:05:09 PM CET 
//


package capaNegocio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the capanegocio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Root_QNAME = new QName("http://xml.netbeans.org/schema/schema", "Root");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: capanegocio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Root }
     * 
     */
    public Root createRoot() {
        return new Root();
    }

    /**
     * Create an instance of {@link Persona }
     * 
     */
    public Persona createPersona() {
        return new Persona();
    }

    /**
     * Create an instance of {@link Negocio }
     * 
     */
    public Negocio createNegocio() {
        return new Negocio();
    }

    /**
     * Create an instance of {@link ListaNegocio }
     * 
     */
    public ListaNegocio createListaNegocio() {
        return new ListaNegocio();
    }

    /**
     * Create an instance of {@link ListaPersona }
     * 
     */
    public ListaPersona createListaPersona() {
        return new ListaPersona();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Root }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/schema", name = "Root")
    public JAXBElement<Root> createRoot(Root value) {
        return new JAXBElement<Root>(_Root_QNAME, Root.class, null, value);
    }

}
