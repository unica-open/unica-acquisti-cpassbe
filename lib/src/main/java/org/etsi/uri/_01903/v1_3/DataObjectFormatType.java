
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DataObjectFormatType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="DataObjectFormatType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ObjectIdentifier" type="{http://uri.etsi.org/01903/v1.3.2#}ObjectIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Encoding" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ObjectReference" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectFormatType", propOrder = {
    "description",
    "objectIdentifier",
    "mimeType",
    "encoding"
})
public class DataObjectFormatType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ObjectIdentifier")
    protected ObjectIdentifierType objectIdentifier;
    @XmlElement(name = "MimeType")
    protected String mimeType;
    @XmlElement(name = "Encoding")
    @XmlSchemaType(name = "anyURI")
    protected String encoding;
    @XmlAttribute(name = "ObjectReference", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String objectReference;

    /**
     * Recupera il valore della proprietà description.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Recupera il valore della proprietà objectIdentifier.
     *
     * @return
     *     possible object is
     *     {@link ObjectIdentifierType }
     *
     */
    public ObjectIdentifierType getObjectIdentifier() {
        return objectIdentifier;
    }

    /**
     * Imposta il valore della proprietà objectIdentifier.
     *
     * @param value
     *     allowed object is
     *     {@link ObjectIdentifierType }
     *
     */
    public void setObjectIdentifier(ObjectIdentifierType value) {
        this.objectIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà mimeType.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Imposta il valore della proprietà mimeType.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Recupera il valore della proprietà encoding.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Imposta il valore della proprietà encoding.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

    /**
     * Recupera il valore della proprietà objectReference.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getObjectReference() {
        return objectReference;
    }

    /**
     * Imposta il valore della proprietà objectReference.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setObjectReference(String value) {
        this.objectReference = value;
    }

}
