
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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per SignedSignaturePropertiesType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SignedSignaturePropertiesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SigningTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="SigningCertificate" type="{http://uri.etsi.org/01903/v1.3.2#}CertIDListType" minOccurs="0"/&gt;
 *         &lt;element name="SignaturePolicyIdentifier" type="{http://uri.etsi.org/01903/v1.3.2#}SignaturePolicyIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="SignatureProductionPlace" type="{http://uri.etsi.org/01903/v1.3.2#}SignatureProductionPlaceType" minOccurs="0"/&gt;
 *         &lt;element name="SignerRole" type="{http://uri.etsi.org/01903/v1.3.2#}SignerRoleType" minOccurs="0"/&gt;
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
@XmlType(name = "SignedSignaturePropertiesType", propOrder = {
    "signingTime",
    "signingCertificate",
    "signaturePolicyIdentifier",
    "signatureProductionPlace",
    "signerRole"
})
public class SignedSignaturePropertiesType {

    @XmlElement(name = "SigningTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signingTime;
    @XmlElement(name = "SigningCertificate")
    protected CertIDListType signingCertificate;
    @XmlElement(name = "SignaturePolicyIdentifier")
    protected SignaturePolicyIdentifierType signaturePolicyIdentifier;
    @XmlElement(name = "SignatureProductionPlace")
    protected SignatureProductionPlaceType signatureProductionPlace;
    @XmlElement(name = "SignerRole")
    protected SignerRoleType signerRole;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Recupera il valore della proprietà signingTime.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getSigningTime() {
        return signingTime;
    }

    /**
     * Imposta il valore della proprietà signingTime.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setSigningTime(XMLGregorianCalendar value) {
        this.signingTime = value;
    }

    /**
     * Recupera il valore della proprietà signingCertificate.
     *
     * @return
     *     possible object is
     *     {@link CertIDListType }
     *
     */
    public CertIDListType getSigningCertificate() {
        return signingCertificate;
    }

    /**
     * Imposta il valore della proprietà signingCertificate.
     *
     * @param value
     *     allowed object is
     *     {@link CertIDListType }
     *
     */
    public void setSigningCertificate(CertIDListType value) {
        this.signingCertificate = value;
    }

    /**
     * Recupera il valore della proprietà signaturePolicyIdentifier.
     *
     * @return
     *     possible object is
     *     {@link SignaturePolicyIdentifierType }
     *
     */
    public SignaturePolicyIdentifierType getSignaturePolicyIdentifier() {
        return signaturePolicyIdentifier;
    }

    /**
     * Imposta il valore della proprietà signaturePolicyIdentifier.
     *
     * @param value
     *     allowed object is
     *     {@link SignaturePolicyIdentifierType }
     *
     */
    public void setSignaturePolicyIdentifier(SignaturePolicyIdentifierType value) {
        this.signaturePolicyIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà signatureProductionPlace.
     *
     * @return
     *     possible object is
     *     {@link SignatureProductionPlaceType }
     *
     */
    public SignatureProductionPlaceType getSignatureProductionPlace() {
        return signatureProductionPlace;
    }

    /**
     * Imposta il valore della proprietà signatureProductionPlace.
     *
     * @param value
     *     allowed object is
     *     {@link SignatureProductionPlaceType }
     *
     */
    public void setSignatureProductionPlace(SignatureProductionPlaceType value) {
        this.signatureProductionPlace = value;
    }

    /**
     * Recupera il valore della proprietà signerRole.
     *
     * @return
     *     possible object is
     *     {@link SignerRoleType }
     *
     */
    public SignerRoleType getSignerRole() {
        return signerRole;
    }

    /**
     * Imposta il valore della proprietà signerRole.
     *
     * @param value
     *     allowed object is
     *     {@link SignerRoleType }
     *
     */
    public void setSignerRole(SignerRoleType value) {
        this.signerRole = value;
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
