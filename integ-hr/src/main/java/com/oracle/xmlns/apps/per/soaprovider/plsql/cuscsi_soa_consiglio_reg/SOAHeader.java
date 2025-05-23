
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per anonymous complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Responsibility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RespApplication" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SecurityGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NLSLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Org_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responsibility",
    "respApplication",
    "securityGroup",
    "nlsLanguage",
    "orgId"
})
@XmlRootElement(name = "SOAHeader")
public class SOAHeader {

    @XmlElement(name = "Responsibility")
    protected String responsibility;
    @XmlElement(name = "RespApplication")
    protected String respApplication;
    @XmlElement(name = "SecurityGroup")
    protected String securityGroup;
    @XmlElement(name = "NLSLanguage")
    protected String nlsLanguage;
    @XmlElement(name = "Org_Id")
    protected String orgId;

    /**
     * Recupera il valore della proprietà responsibility.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsibility() {
        return responsibility;
    }

    /**
     * Imposta il valore della proprietà responsibility.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsibility(String value) {
        this.responsibility = value;
    }

    /**
     * Recupera il valore della proprietà respApplication.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespApplication() {
        return respApplication;
    }

    /**
     * Imposta il valore della proprietà respApplication.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespApplication(String value) {
        this.respApplication = value;
    }

    /**
     * Recupera il valore della proprietà securityGroup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityGroup() {
        return securityGroup;
    }

    /**
     * Imposta il valore della proprietà securityGroup.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityGroup(String value) {
        this.securityGroup = value;
    }

    /**
     * Recupera il valore della proprietà nlsLanguage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNLSLanguage() {
        return nlsLanguage;
    }

    /**
     * Imposta il valore della proprietà nlsLanguage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNLSLanguage(String value) {
        this.nlsLanguage = value;
    }

    /**
     * Recupera il valore della proprietà orgId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * Imposta il valore della proprietà orgId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgId(String value) {
        this.orgId = value;
    }

}
