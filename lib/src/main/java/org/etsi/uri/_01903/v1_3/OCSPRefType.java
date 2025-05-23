
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per OCSPRefType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="OCSPRefType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OCSPIdentifier" type="{http://uri.etsi.org/01903/v1.3.2#}OCSPIdentifierType"/&gt;
 *         &lt;element name="DigestAlgAndValue" type="{http://uri.etsi.org/01903/v1.3.2#}DigestAlgAndValueType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OCSPRefType", propOrder = {
    "ocspIdentifier",
    "digestAlgAndValue"
})
public class OCSPRefType {

    @XmlElement(name = "OCSPIdentifier", required = true)
    protected OCSPIdentifierType ocspIdentifier;
    @XmlElement(name = "DigestAlgAndValue")
    protected DigestAlgAndValueType digestAlgAndValue;

    /**
     * Recupera il valore della proprietà ocspIdentifier.
     *
     * @return
     *     possible object is
     *     {@link OCSPIdentifierType }
     *
     */
    public OCSPIdentifierType getOCSPIdentifier() {
        return ocspIdentifier;
    }

    /**
     * Imposta il valore della proprietà ocspIdentifier.
     *
     * @param value
     *     allowed object is
     *     {@link OCSPIdentifierType }
     *
     */
    public void setOCSPIdentifier(OCSPIdentifierType value) {
        this.ocspIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà digestAlgAndValue.
     *
     * @return
     *     possible object is
     *     {@link DigestAlgAndValueType }
     *
     */
    public DigestAlgAndValueType getDigestAlgAndValue() {
        return digestAlgAndValue;
    }

    /**
     * Imposta il valore della proprietà digestAlgAndValue.
     *
     * @param value
     *     allowed object is
     *     {@link DigestAlgAndValueType }
     *
     */
    public void setDigestAlgAndValue(DigestAlgAndValueType value) {
        this.digestAlgAndValue = value;
    }

}
