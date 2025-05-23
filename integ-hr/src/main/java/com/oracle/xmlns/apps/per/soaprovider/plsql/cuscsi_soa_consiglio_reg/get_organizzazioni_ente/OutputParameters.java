
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_organizzazioni_ente;

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
 *         &amp;lt;element name="OP_NOME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_TIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_TIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_INIZIO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_FINE" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_UBICAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DESCRIZIONE_ESTESA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_STRUTTURA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PROVVEDIMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_PROVVEDIMENTO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_TELEFONO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_TELEFONO2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_TELEFONO3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_FAX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_AOO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_ORIGINE_STRUTTURA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_REGIONALE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_STRUTTURA_PADRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_TIPOLOGIA_PADRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "opnome",
    "opcodtipologia",
    "opdestipologia",
    "opdatainizio",
    "opdatafine",
    "opcodubicazione",
    "opdescrizioneestesa",
    "opcodstruttura",
    "opprovvedimento",
    "opdataprovvedimento",
    "optelefono",
    "optelefono2",
    "optelefono3",
    "opfax",
    "opemail",
    "opaoo",
    "oporiginestruttura",
    "opcodregionale",
    "opcodstrutturapadre",
    "opcodtipologiapadre",
    "opmsgdata"
})
@XmlRootElement(name = "OutputParameters")
public class OutputParameters {

    @XmlElementRef(name = "OP_NOME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opnome;
    @XmlElementRef(name = "OP_COD_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodtipologia;
    @XmlElementRef(name = "OP_DES_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdestipologia;
    @XmlElementRef(name = "OP_DATA_INIZIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdatainizio;
    @XmlElementRef(name = "OP_DATA_FINE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdatafine;
    @XmlElementRef(name = "OP_COD_UBICAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodubicazione;
    @XmlElementRef(name = "OP_DESCRIZIONE_ESTESA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdescrizioneestesa;
    @XmlElementRef(name = "OP_COD_STRUTTURA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodstruttura;
    @XmlElementRef(name = "OP_PROVVEDIMENTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opprovvedimento;
    @XmlElementRef(name = "OP_DATA_PROVVEDIMENTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdataprovvedimento;
    @XmlElementRef(name = "OP_TELEFONO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optelefono;
    @XmlElementRef(name = "OP_TELEFONO2", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optelefono2;
    @XmlElementRef(name = "OP_TELEFONO3", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optelefono3;
    @XmlElementRef(name = "OP_FAX", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opfax;
    @XmlElementRef(name = "OP_EMAIL", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opemail;
    @XmlElementRef(name = "OP_AOO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opaoo;
    @XmlElementRef(name = "OP_ORIGINE_STRUTTURA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oporiginestruttura;
    @XmlElementRef(name = "OP_COD_REGIONALE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodregionale;
    @XmlElementRef(name = "OP_COD_STRUTTURA_PADRE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodstrutturapadre;
    @XmlElementRef(name = "OP_COD_TIPOLOGIA_PADRE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodtipologiapadre;
    @XmlElementRef(name = "OP_MSG_DATA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmsgdata;

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
     * Recupera il valore della proprietà opcodtipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODTIPOLOGIA() {
        return opcodtipologia;
    }

    /**
     * Imposta il valore della proprietà opcodtipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODTIPOLOGIA(JAXBElement<String> value) {
        this.opcodtipologia = value;
    }

    /**
     * Recupera il valore della proprietà opdestipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESTIPOLOGIA() {
        return opdestipologia;
    }

    /**
     * Imposta il valore della proprietà opdestipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESTIPOLOGIA(JAXBElement<String> value) {
        this.opdestipologia = value;
    }

    /**
     * Recupera il valore della proprietà opdatainizio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAINIZIO() {
        return opdatainizio;
    }

    /**
     * Imposta il valore della proprietà opdatainizio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAINIZIO(JAXBElement<XMLGregorianCalendar> value) {
        this.opdatainizio = value;
    }

    /**
     * Recupera il valore della proprietà opdatafine.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAFINE() {
        return opdatafine;
    }

    /**
     * Imposta il valore della proprietà opdatafine.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAFINE(JAXBElement<XMLGregorianCalendar> value) {
        this.opdatafine = value;
    }

    /**
     * Recupera il valore della proprietà opcodubicazione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODUBICAZIONE() {
        return opcodubicazione;
    }

    /**
     * Imposta il valore della proprietà opcodubicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODUBICAZIONE(JAXBElement<String> value) {
        this.opcodubicazione = value;
    }

    /**
     * Recupera il valore della proprietà opdescrizioneestesa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESCRIZIONEESTESA() {
        return opdescrizioneestesa;
    }

    /**
     * Imposta il valore della proprietà opdescrizioneestesa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESCRIZIONEESTESA(JAXBElement<String> value) {
        this.opdescrizioneestesa = value;
    }

    /**
     * Recupera il valore della proprietà opcodstruttura.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODSTRUTTURA() {
        return opcodstruttura;
    }

    /**
     * Imposta il valore della proprietà opcodstruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODSTRUTTURA(JAXBElement<String> value) {
        this.opcodstruttura = value;
    }

    /**
     * Recupera il valore della proprietà opprovvedimento.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPPROVVEDIMENTO() {
        return opprovvedimento;
    }

    /**
     * Imposta il valore della proprietà opprovvedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPPROVVEDIMENTO(JAXBElement<String> value) {
        this.opprovvedimento = value;
    }

    /**
     * Recupera il valore della proprietà opdataprovvedimento.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAPROVVEDIMENTO() {
        return opdataprovvedimento;
    }

    /**
     * Imposta il valore della proprietà opdataprovvedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAPROVVEDIMENTO(JAXBElement<XMLGregorianCalendar> value) {
        this.opdataprovvedimento = value;
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
     * Recupera il valore della proprietà optelefono2.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPTELEFONO2() {
        return optelefono2;
    }

    /**
     * Imposta il valore della proprietà optelefono2.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPTELEFONO2(JAXBElement<String> value) {
        this.optelefono2 = value;
    }

    /**
     * Recupera il valore della proprietà optelefono3.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPTELEFONO3() {
        return optelefono3;
    }

    /**
     * Imposta il valore della proprietà optelefono3.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPTELEFONO3(JAXBElement<String> value) {
        this.optelefono3 = value;
    }

    /**
     * Recupera il valore della proprietà opfax.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPFAX() {
        return opfax;
    }

    /**
     * Imposta il valore della proprietà opfax.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPFAX(JAXBElement<String> value) {
        this.opfax = value;
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
     * Recupera il valore della proprietà opaoo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPAOO() {
        return opaoo;
    }

    /**
     * Imposta il valore della proprietà opaoo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPAOO(JAXBElement<String> value) {
        this.opaoo = value;
    }

    /**
     * Recupera il valore della proprietà oporiginestruttura.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPORIGINESTRUTTURA() {
        return oporiginestruttura;
    }

    /**
     * Imposta il valore della proprietà oporiginestruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPORIGINESTRUTTURA(JAXBElement<String> value) {
        this.oporiginestruttura = value;
    }

    /**
     * Recupera il valore della proprietà opcodregionale.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODREGIONALE() {
        return opcodregionale;
    }

    /**
     * Imposta il valore della proprietà opcodregionale.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODREGIONALE(JAXBElement<String> value) {
        this.opcodregionale = value;
    }

    /**
     * Recupera il valore della proprietà opcodstrutturapadre.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODSTRUTTURAPADRE() {
        return opcodstrutturapadre;
    }

    /**
     * Imposta il valore della proprietà opcodstrutturapadre.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODSTRUTTURAPADRE(JAXBElement<String> value) {
        this.opcodstrutturapadre = value;
    }

    /**
     * Recupera il valore della proprietà opcodtipologiapadre.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODTIPOLOGIAPADRE() {
        return opcodtipologiapadre;
    }

    /**
     * Imposta il valore della proprietà opcodtipologiapadre.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODTIPOLOGIAPADRE(JAXBElement<String> value) {
        this.opcodtipologiapadre = value;
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
