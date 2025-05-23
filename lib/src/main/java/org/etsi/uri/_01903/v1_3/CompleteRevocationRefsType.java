
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
 * <p>Classe Java per CompleteRevocationRefsType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="CompleteRevocationRefsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CRLRefs" type="{http://uri.etsi.org/01903/v1.3.2#}CRLRefsType" minOccurs="0"/&gt;
 *         &lt;element name="OCSPRefs" type="{http://uri.etsi.org/01903/v1.3.2#}OCSPRefsType" minOccurs="0"/&gt;
 *         &lt;element name="OtherRefs" type="{http://uri.etsi.org/01903/v1.3.2#}OtherCertStatusRefsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompleteRevocationRefsType", propOrder = {
    "crlRefs",
    "ocspRefs",
    "otherRefs"
})
public class CompleteRevocationRefsType {

    @XmlElement(name = "CRLRefs")
    protected CRLRefsType crlRefs;
    @XmlElement(name = "OCSPRefs")
    protected OCSPRefsType ocspRefs;
    @XmlElement(name = "OtherRefs")
    protected OtherCertStatusRefsType otherRefs;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Recupera il valore della proprietà crlRefs.
     *
     * @return
     *     possible object is
     *     {@link CRLRefsType }
     *
     */
    public CRLRefsType getCRLRefs() {
        return crlRefs;
    }

    /**
     * Imposta il valore della proprietà crlRefs.
     *
     * @param value
     *     allowed object is
     *     {@link CRLRefsType }
     *
     */
    public void setCRLRefs(CRLRefsType value) {
        this.crlRefs = value;
    }

    /**
     * Recupera il valore della proprietà ocspRefs.
     *
     * @return
     *     possible object is
     *     {@link OCSPRefsType }
     *
     */
    public OCSPRefsType getOCSPRefs() {
        return ocspRefs;
    }

    /**
     * Imposta il valore della proprietà ocspRefs.
     *
     * @param value
     *     allowed object is
     *     {@link OCSPRefsType }
     *
     */
    public void setOCSPRefs(OCSPRefsType value) {
        this.ocspRefs = value;
    }

    /**
     * Recupera il valore della proprietà otherRefs.
     *
     * @return
     *     possible object is
     *     {@link OtherCertStatusRefsType }
     *
     */
    public OtherCertStatusRefsType getOtherRefs() {
        return otherRefs;
    }

    /**
     * Imposta il valore della proprietà otherRefs.
     *
     * @param value
     *     allowed object is
     *     {@link OtherCertStatusRefsType }
     *
     */
    public void setOtherRefs(OtherCertStatusRefsType value) {
        this.otherRefs = value;
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
