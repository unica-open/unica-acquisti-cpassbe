
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SignerRoleType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SignerRoleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ClaimedRoles" type="{http://uri.etsi.org/01903/v1.3.2#}ClaimedRolesListType" minOccurs="0"/&gt;
 *         &lt;element name="CertifiedRoles" type="{http://uri.etsi.org/01903/v1.3.2#}CertifiedRolesListType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignerRoleType", propOrder = {
    "claimedRoles",
    "certifiedRoles"
})
public class SignerRoleType {

    @XmlElement(name = "ClaimedRoles")
    protected ClaimedRolesListType claimedRoles;
    @XmlElement(name = "CertifiedRoles")
    protected CertifiedRolesListType certifiedRoles;

    /**
     * Recupera il valore della proprietà claimedRoles.
     *
     * @return
     *     possible object is
     *     {@link ClaimedRolesListType }
     *
     */
    public ClaimedRolesListType getClaimedRoles() {
        return claimedRoles;
    }

    /**
     * Imposta il valore della proprietà claimedRoles.
     *
     * @param value
     *     allowed object is
     *     {@link ClaimedRolesListType }
     *
     */
    public void setClaimedRoles(ClaimedRolesListType value) {
        this.claimedRoles = value;
    }

    /**
     * Recupera il valore della proprietà certifiedRoles.
     *
     * @return
     *     possible object is
     *     {@link CertifiedRolesListType }
     *
     */
    public CertifiedRolesListType getCertifiedRoles() {
        return certifiedRoles;
    }

    /**
     * Imposta il valore della proprietà certifiedRoles.
     *
     * @param value
     *     allowed object is
     *     {@link CertifiedRolesListType }
     *
     */
    public void setCertifiedRoles(CertifiedRolesListType value) {
        this.certifiedRoles = value;
    }

}
