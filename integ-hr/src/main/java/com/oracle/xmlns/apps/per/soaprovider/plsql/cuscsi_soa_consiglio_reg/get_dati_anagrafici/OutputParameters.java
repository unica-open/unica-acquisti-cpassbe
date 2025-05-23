
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_dati_anagrafici;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &amp;lt;element name="OP_MATRICOLA_HR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_CODICE_FISCALE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COGNOME_NOME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COGNOME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_NOME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_SESSO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_NASCITA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_NAZIONE_NASCITA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_NAZIONE_NASCITA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_LUOGO_NASCITA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_LUOGO_NASCITA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_EX_COD_ENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_EX_COD_ENTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_EX_MATRICOLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_TIPO_PERSONA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_CITTADINANZA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_PRIMA_ASSUNZIONE" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_ULT_ASSUNZIONE" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "opmatricolahr",
    "opcodicefiscale",
    "opcognomenome",
    "opcognome",
    "opnome",
    "opsesso",
    "opdatanascita",
    "opcodnazionenascita",
    "opdesnazionenascita",
    "opcodluogonascita",
    "opdesluogonascita",
    "opexcodente",
    "opdesexcodente",
    "opexmatricola",
    "optipopersona",
    "opcittadinanza",
    "opdataprimaassunzione",
    "opdataultassunzione",
    "opemail",
    "opmsgdata"
})
@XmlRootElement(name = "OutputParameters")
public class OutputParameters {

    @XmlElementRef(name = "OP_MATRICOLA_HR", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmatricolahr;
    @XmlElementRef(name = "OP_CODICE_FISCALE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodicefiscale;
    @XmlElementRef(name = "OP_COGNOME_NOME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcognomenome;
    @XmlElementRef(name = "OP_COGNOME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcognome;
    @XmlElementRef(name = "OP_NOME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opnome;
    @XmlElementRef(name = "OP_SESSO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opsesso;
    @XmlElementRef(name = "OP_DATA_NASCITA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdatanascita;
    @XmlElementRef(name = "OP_COD_NAZIONE_NASCITA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodnazionenascita;
    @XmlElementRef(name = "OP_DES_NAZIONE_NASCITA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesnazionenascita;
    @XmlElementRef(name = "OP_COD_LUOGO_NASCITA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodluogonascita;
    @XmlElementRef(name = "OP_DES_LUOGO_NASCITA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesluogonascita;
    @XmlElementRef(name = "OP_EX_COD_ENTE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opexcodente;
    @XmlElementRef(name = "OP_DES_EX_COD_ENTE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesexcodente;
    @XmlElementRef(name = "OP_EX_MATRICOLA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opexmatricola;
    @XmlElementRef(name = "OP_TIPO_PERSONA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optipopersona;
    @XmlElementRef(name = "OP_CITTADINANZA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcittadinanza;
    @XmlElementRef(name = "OP_DATA_PRIMA_ASSUNZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdataprimaassunzione;
    @XmlElementRef(name = "OP_DATA_ULT_ASSUNZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdataultassunzione;
    @XmlElementRef(name = "OP_EMAIL", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opemail;
    @XmlElementRef(name = "OP_MSG_DATA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_anagrafici/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmsgdata;

    /**
     * Recupera il valore della proprietà opmatricolahr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPMATRICOLAHR() {
        return opmatricolahr;
    }

    /**
     * Imposta il valore della proprietà opmatricolahr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPMATRICOLAHR(JAXBElement<String> value) {
        this.opmatricolahr = value;
    }

    /**
     * Recupera il valore della proprietà opcodicefiscale.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODICEFISCALE() {
        return opcodicefiscale;
    }

    /**
     * Imposta il valore della proprietà opcodicefiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODICEFISCALE(JAXBElement<String> value) {
        this.opcodicefiscale = value;
    }

    /**
     * Recupera il valore della proprietà opcognomenome.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCOGNOMENOME() {
        return opcognomenome;
    }

    /**
     * Imposta il valore della proprietà opcognomenome.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCOGNOMENOME(JAXBElement<String> value) {
        this.opcognomenome = value;
    }

    /**
     * Recupera il valore della proprietà opcognome.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCOGNOME() {
        return opcognome;
    }

    /**
     * Imposta il valore della proprietà opcognome.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCOGNOME(JAXBElement<String> value) {
        this.opcognome = value;
    }

    /**
     * Recupera il valore della proprietà opnome.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPNOME() {
        return opnome;
    }

    /**
     * Imposta il valore della proprietà opnome.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPNOME(JAXBElement<String> value) {
        this.opnome = value;
    }

    /**
     * Recupera il valore della proprietà opsesso.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPSESSO() {
        return opsesso;
    }

    /**
     * Imposta il valore della proprietà opsesso.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPSESSO(JAXBElement<String> value) {
        this.opsesso = value;
    }

    /**
     * Recupera il valore della proprietà opdatanascita.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATANASCITA() {
        return opdatanascita;
    }

    /**
     * Imposta il valore della proprietà opdatanascita.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATANASCITA(JAXBElement<XMLGregorianCalendar> value) {
        this.opdatanascita = value;
    }

    /**
     * Recupera il valore della proprietà opcodnazionenascita.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODNAZIONENASCITA() {
        return opcodnazionenascita;
    }

    /**
     * Imposta il valore della proprietà opcodnazionenascita.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODNAZIONENASCITA(JAXBElement<String> value) {
        this.opcodnazionenascita = value;
    }

    /**
     * Recupera il valore della proprietà opdesnazionenascita.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESNAZIONENASCITA() {
        return opdesnazionenascita;
    }

    /**
     * Imposta il valore della proprietà opdesnazionenascita.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESNAZIONENASCITA(JAXBElement<String> value) {
        this.opdesnazionenascita = value;
    }

    /**
     * Recupera il valore della proprietà opcodluogonascita.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODLUOGONASCITA() {
        return opcodluogonascita;
    }

    /**
     * Imposta il valore della proprietà opcodluogonascita.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODLUOGONASCITA(JAXBElement<String> value) {
        this.opcodluogonascita = value;
    }

    /**
     * Recupera il valore della proprietà opdesluogonascita.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESLUOGONASCITA() {
        return opdesluogonascita;
    }

    /**
     * Imposta il valore della proprietà opdesluogonascita.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESLUOGONASCITA(JAXBElement<String> value) {
        this.opdesluogonascita = value;
    }

    /**
     * Recupera il valore della proprietà opexcodente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPEXCODENTE() {
        return opexcodente;
    }

    /**
     * Imposta il valore della proprietà opexcodente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPEXCODENTE(JAXBElement<String> value) {
        this.opexcodente = value;
    }

    /**
     * Recupera il valore della proprietà opdesexcodente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESEXCODENTE() {
        return opdesexcodente;
    }

    /**
     * Imposta il valore della proprietà opdesexcodente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESEXCODENTE(JAXBElement<String> value) {
        this.opdesexcodente = value;
    }

    /**
     * Recupera il valore della proprietà opexmatricola.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPEXMATRICOLA() {
        return opexmatricola;
    }

    /**
     * Imposta il valore della proprietà opexmatricola.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPEXMATRICOLA(JAXBElement<String> value) {
        this.opexmatricola = value;
    }

    /**
     * Recupera il valore della proprietà optipopersona.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPTIPOPERSONA() {
        return optipopersona;
    }

    /**
     * Imposta il valore della proprietà optipopersona.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPTIPOPERSONA(JAXBElement<String> value) {
        this.optipopersona = value;
    }

    /**
     * Recupera il valore della proprietà opcittadinanza.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCITTADINANZA() {
        return opcittadinanza;
    }

    /**
     * Imposta il valore della proprietà opcittadinanza.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCITTADINANZA(JAXBElement<String> value) {
        this.opcittadinanza = value;
    }

    /**
     * Recupera il valore della proprietà opdataprimaassunzione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAPRIMAASSUNZIONE() {
        return opdataprimaassunzione;
    }

    /**
     * Imposta il valore della proprietà opdataprimaassunzione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAPRIMAASSUNZIONE(JAXBElement<XMLGregorianCalendar> value) {
        this.opdataprimaassunzione = value;
    }

    /**
     * Recupera il valore della proprietà opdataultassunzione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAULTASSUNZIONE() {
        return opdataultassunzione;
    }

    /**
     * Imposta il valore della proprietà opdataultassunzione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAULTASSUNZIONE(JAXBElement<XMLGregorianCalendar> value) {
        this.opdataultassunzione = value;
    }

    /**
     * Recupera il valore della proprietà opemail.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPEMAIL() {
        return opemail;
    }

    /**
     * Imposta il valore della proprietà opemail.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPEMAIL(JAXBElement<String> value) {
        this.opemail = value;
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
