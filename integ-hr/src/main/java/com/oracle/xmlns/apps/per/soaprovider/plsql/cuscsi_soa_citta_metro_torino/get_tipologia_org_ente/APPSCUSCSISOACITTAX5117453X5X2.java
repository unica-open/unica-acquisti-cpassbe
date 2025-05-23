
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_tipologia_org_ente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per APPS.CUSCSI_SOA_CITTAX5117453X5X2 complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="APPS.CUSCSI_SOA_CITTAX5117453X5X2"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="COD_TIPOLOGIA" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="300"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="DES_TIPOLOGIA" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="300"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="DATA_INIZIO" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="300"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="DATA_FINE" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="300"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APPS.CUSCSI_SOA_CITTAX5117453X5X2", propOrder = {
    "codtipologia",
    "destipologia",
    "datainizio",
    "datafine"
})
public class APPSCUSCSISOACITTAX5117453X5X2 {

    @XmlElementRef(name = "COD_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codtipologia;
    @XmlElementRef(name = "DES_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destipologia;
    @XmlElementRef(name = "DATA_INIZIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> datainizio;
    @XmlElementRef(name = "DATA_FINE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> datafine;

    /**
     * Recupera il valore della proprietà codtipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCODTIPOLOGIA() {
        return codtipologia;
    }

    /**
     * Imposta il valore della proprietà codtipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCODTIPOLOGIA(JAXBElement<String> value) {
        this.codtipologia = value;
    }

    /**
     * Recupera il valore della proprietà destipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDESTIPOLOGIA() {
        return destipologia;
    }

    /**
     * Imposta il valore della proprietà destipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDESTIPOLOGIA(JAXBElement<String> value) {
        this.destipologia = value;
    }

    /**
     * Recupera il valore della proprietà datainizio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDATAINIZIO() {
        return datainizio;
    }

    /**
     * Imposta il valore della proprietà datainizio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDATAINIZIO(JAXBElement<String> value) {
        this.datainizio = value;
    }

    /**
     * Recupera il valore della proprietà datafine.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDATAFINE() {
        return datafine;
    }

    /**
     * Imposta il valore della proprietà datafine.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDATAFINE(JAXBElement<String> value) {
        this.datafine = value;
    }

}
