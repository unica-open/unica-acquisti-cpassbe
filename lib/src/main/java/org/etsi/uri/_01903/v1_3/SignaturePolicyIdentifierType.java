
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SignaturePolicyIdentifierType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SignaturePolicyIdentifierType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="SignaturePolicyId" type="{http://uri.etsi.org/01903/v1.3.2#}SignaturePolicyIdType"/&gt;
 *         &lt;element name="SignaturePolicyImplied" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignaturePolicyIdentifierType", propOrder = {
    "signaturePolicyId",
    "signaturePolicyImplied"
})
public class SignaturePolicyIdentifierType {

    @XmlElement(name = "SignaturePolicyId")
    protected SignaturePolicyIdType signaturePolicyId;
    @XmlElement(name = "SignaturePolicyImplied")
    protected Object signaturePolicyImplied;

    /**
     * Recupera il valore della proprietà signaturePolicyId.
     *
     * @return
     *     possible object is
     *     {@link SignaturePolicyIdType }
     *
     */
    public SignaturePolicyIdType getSignaturePolicyId() {
        return signaturePolicyId;
    }

    /**
     * Imposta il valore della proprietà signaturePolicyId.
     *
     * @param value
     *     allowed object is
     *     {@link SignaturePolicyIdType }
     *
     */
    public void setSignaturePolicyId(SignaturePolicyIdType value) {
        this.signaturePolicyId = value;
    }

    /**
     * Recupera il valore della proprietà signaturePolicyImplied.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getSignaturePolicyImplied() {
        return signaturePolicyImplied;
    }

    /**
     * Imposta il valore della proprietà signaturePolicyImplied.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setSignaturePolicyImplied(Object value) {
        this.signaturePolicyImplied = value;
    }

}
