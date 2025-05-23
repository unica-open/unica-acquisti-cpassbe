
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_indirizzo_dip;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &amp;lt;element name="OP_TIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_INDIRIZZO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_CIVICO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_BIS_INTERNO_LETT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_CAP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_CITTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_NAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_TELEFONO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_ALTRO_TELEFONO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_MSG_DATA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "optipologia",
    "opindirizzo",
    "opcivico",
    "opbisinternolett",
    "opcap",
    "opcitta",
    "opprovincia",
    "opnazione",
    "optelefono",
    "opaltrotelefono",
    "opmsgdata"
})
@XmlRootElement(name = "OutputParameters")
public class OutputParameters {

    @XmlElementRef(name = "OP_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optipologia;
    @XmlElementRef(name = "OP_INDIRIZZO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opindirizzo;
    @XmlElementRef(name = "OP_CIVICO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcivico;
    @XmlElementRef(name = "OP_BIS_INTERNO_LETT", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opbisinternolett;
    @XmlElementRef(name = "OP_CAP", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcap;
    @XmlElementRef(name = "OP_CITTA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcitta;
    @XmlElementRef(name = "OP_PROVINCIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opprovincia;
    @XmlElementRef(name = "OP_NAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opnazione;
    @XmlElementRef(name = "OP_TELEFONO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optelefono;
    @XmlElementRef(name = "OP_ALTRO_TELEFONO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opaltrotelefono;
    @XmlElementRef(name = "OP_MSG_DATA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_indirizzo_dip/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmsgdata;

    /**
     * Recupera il valore della proprietà optipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPTIPOLOGIA() {
        return optipologia;
    }

    /**
     * Imposta il valore della proprietà optipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPTIPOLOGIA(JAXBElement<String> value) {
        this.optipologia = value;
    }

    /**
     * Recupera il valore della proprietà opindirizzo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPINDIRIZZO() {
        return opindirizzo;
    }

    /**
     * Imposta il valore della proprietà opindirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPINDIRIZZO(JAXBElement<String> value) {
        this.opindirizzo = value;
    }

    /**
     * Recupera il valore della proprietà opcivico.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCIVICO() {
        return opcivico;
    }

    /**
     * Imposta il valore della proprietà opcivico.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCIVICO(JAXBElement<String> value) {
        this.opcivico = value;
    }

    /**
     * Recupera il valore della proprietà opbisinternolett.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPBISINTERNOLETT() {
        return opbisinternolett;
    }

    /**
     * Imposta il valore della proprietà opbisinternolett.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPBISINTERNOLETT(JAXBElement<String> value) {
        this.opbisinternolett = value;
    }

    /**
     * Recupera il valore della proprietà opcap.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCAP() {
        return opcap;
    }

    /**
     * Imposta il valore della proprietà opcap.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCAP(JAXBElement<String> value) {
        this.opcap = value;
    }

    /**
     * Recupera il valore della proprietà opcitta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCITTA() {
        return opcitta;
    }

    /**
     * Imposta il valore della proprietà opcitta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCITTA(JAXBElement<String> value) {
        this.opcitta = value;
    }

    /**
     * Recupera il valore della proprietà opprovincia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPPROVINCIA() {
        return opprovincia;
    }

    /**
     * Imposta il valore della proprietà opprovincia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPPROVINCIA(JAXBElement<String> value) {
        this.opprovincia = value;
    }

    /**
     * Recupera il valore della proprietà opnazione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPNAZIONE() {
        return opnazione;
    }

    /**
     * Imposta il valore della proprietà opnazione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPNAZIONE(JAXBElement<String> value) {
        this.opnazione = value;
    }

    /**
     * Recupera il valore della proprietà optelefono.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPTELEFONO() {
        return optelefono;
    }

    /**
     * Imposta il valore della proprietà optelefono.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPTELEFONO(JAXBElement<String> value) {
        this.optelefono = value;
    }

    /**
     * Recupera il valore della proprietà opaltrotelefono.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPALTROTELEFONO() {
        return opaltrotelefono;
    }

    /**
     * Imposta il valore della proprietà opaltrotelefono.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPALTROTELEFONO(JAXBElement<String> value) {
        this.opaltrotelefono = value;
    }

    /**
     * Recupera il valore della proprietà opmsgdata.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPMSGDATA() {
        return opmsgdata;
    }

    /**
     * Imposta il valore della proprietà opmsgdata.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPMSGDATA(JAXBElement<String> value) {
        this.opmsgdata = value;
    }

}
