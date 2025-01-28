//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.14 a las 01:05:09 PM CET 
//


package capaNegocio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Root complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Root">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaPersona" type="{http://xml.netbeans.org/schema/schema}ListaPersona"/>
 *         &lt;element name="ListaNegocio" type="{http://xml.netbeans.org/schema/schema}ListaNegocio"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Root", propOrder = {
    "listaPersona",
    "listaNegocio"
})
public class Root {

    @XmlElement(name = "ListaPersona", required = true)
    protected ListaPersona listaPersona;
    @XmlElement(name = "ListaNegocio", required = true)
    protected ListaNegocio listaNegocio;

    /**
     * Obtiene el valor de la propiedad listaPersona.
     * 
     * @return
     *     possible object is
     *     {@link ListaPersona }
     *     
     */
    public ListaPersona getListaPersona() {
        return listaPersona;
    }

    /**
     * Define el valor de la propiedad listaPersona.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPersona }
     *     
     */
    public void setListaPersona(ListaPersona value) {
        this.listaPersona = value;
    }

    /**
     * Obtiene el valor de la propiedad listaNegocio.
     * 
     * @return
     *     possible object is
     *     {@link ListaNegocio }
     *     
     */
    public ListaNegocio getListaNegocio() {
        return listaNegocio;
    }

    /**
     * Define el valor de la propiedad listaNegocio.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaNegocio }
     *     
     */
    public void setListaNegocio(ListaNegocio value) {
        this.listaNegocio = value;
    }

}
