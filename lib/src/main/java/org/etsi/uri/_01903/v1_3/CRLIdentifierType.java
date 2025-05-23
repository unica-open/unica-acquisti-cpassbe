
package org.etsi.uri._01903.v1_3;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per CRLIdentifierType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="CRLIdentifierType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Issuer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IssueTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="URI" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRLIdentifierType", propOrder = {
    "issuer",
    "issueTime",
    "number"
})
public class CRLIdentifierType {

    @XmlElement(name = "Issuer", required = true)
    protected String issuer;
    @XmlElement(name = "IssueTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar issueTime;
    @XmlElement(name = "Number")
    protected BigInteger number;
    @XmlAttribute(name = "URI")
    @XmlSchemaType(name = "anyURI")
    protected String uri;

    /**
     * Recupera il valore della proprietà issuer.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Imposta il valore della proprietà issuer.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Recupera il valore della proprietà issueTime.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getIssueTime() {
        return issueTime;
    }

    /**
     * Imposta il valore della proprietà issueTime.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setIssueTime(XMLGregorianCalendar value) {
        this.issueTime = value;
    }

    /**
     * Recupera il valore della proprietà number.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Imposta il valore della proprietà number.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Recupera il valore della proprietà uri.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getURI() {
        return uri;
    }

    /**
     * Imposta il valore della proprietà uri.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setURI(String value) {
        this.uri = value;
    }

}
