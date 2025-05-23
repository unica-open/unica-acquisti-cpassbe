
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per QualifyingPropertiesType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="QualifyingPropertiesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignedProperties" type="{http://uri.etsi.org/01903/v1.3.2#}SignedPropertiesType" minOccurs="0"/&gt;
 *         &lt;element name="UnsignedProperties" type="{http://uri.etsi.org/01903/v1.3.2#}UnsignedPropertiesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Target" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QualifyingPropertiesType", propOrder = {
    "signedProperties",
    "unsignedProperties"
})
public class QualifyingPropertiesType {

    @XmlElement(name = "SignedProperties")
    protected SignedPropertiesType signedProperties;
    @XmlElement(name = "UnsignedProperties")
    protected UnsignedPropertiesType unsignedProperties;
    @XmlAttribute(name = "Target", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String target;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Recupera il valore della proprietà signedProperties.
     *
     * @return
     *     possible object is
     *     {@link SignedPropertiesType }
     *
     */
    public SignedPropertiesType getSignedProperties() {
        return signedProperties;
    }

    /**
     * Imposta il valore della proprietà signedProperties.
     *
     * @param value
     *     allowed object is
     *     {@link SignedPropertiesType }
     *
     */
    public void setSignedProperties(SignedPropertiesType value) {
        this.signedProperties = value;
    }

    /**
     * Recupera il valore della proprietà unsignedProperties.
     *
     * @return
     *     possible object is
     *     {@link UnsignedPropertiesType }
     *
     */
    public UnsignedPropertiesType getUnsignedProperties() {
        return unsignedProperties;
    }

    /**
     * Imposta il valore della proprietà unsignedProperties.
     *
     * @param value
     *     allowed object is
     *     {@link UnsignedPropertiesType }
     *
     */
    public void setUnsignedProperties(UnsignedPropertiesType value) {
        this.unsignedProperties = value;
    }

    /**
     * Recupera il valore della proprietà target.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTarget() {
        return target;
    }

    /**
     * Imposta il valore della proprietà target.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Recupera il valore della proprietà id.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

}
