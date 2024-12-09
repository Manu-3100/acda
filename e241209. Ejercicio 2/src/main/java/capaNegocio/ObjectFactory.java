//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.12.07 a las 01:05:13 PM CET 
//


package capaNegocio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the capaNegocio package. 
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

    private final static QName _ModelVersion_QNAME = new QName("http://maven.apache.org/POM/4.0.0", "modelVersion");
    private final static QName _GroupId_QNAME = new QName("http://maven.apache.org/POM/4.0.0", "groupId");
    private final static QName _Scope_QNAME = new QName("http://maven.apache.org/POM/4.0.0", "scope");
    private final static QName _Version_QNAME = new QName("http://maven.apache.org/POM/4.0.0", "version");
    private final static QName _ArtifactId_QNAME = new QName("http://maven.apache.org/POM/4.0.0", "artifactId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: capaNegocio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Dependency }
     * 
     */
    public Dependency createDependency() {
        return new Dependency();
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link Dependencies }
     * 
     */
    public Dependencies createDependencies() {
        return new Dependencies();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://maven.apache.org/POM/4.0.0", name = "modelVersion")
    public JAXBElement<String> createModelVersion(String value) {
        return new JAXBElement<String>(_ModelVersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://maven.apache.org/POM/4.0.0", name = "groupId")
    public JAXBElement<String> createGroupId(String value) {
        return new JAXBElement<String>(_GroupId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://maven.apache.org/POM/4.0.0", name = "scope")
    public JAXBElement<String> createScope(String value) {
        return new JAXBElement<String>(_Scope_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://maven.apache.org/POM/4.0.0", name = "version")
    public JAXBElement<String> createVersion(String value) {
        return new JAXBElement<String>(_Version_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://maven.apache.org/POM/4.0.0", name = "artifactId")
    public JAXBElement<String> createArtifactId(String value) {
        return new JAXBElement<String>(_ArtifactId_QNAME, String.class, null, value);
    }

}
