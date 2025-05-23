
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_citta_metro_torino.get_dati_assegnazione;

import java.math.BigDecimal;

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
 *         &amp;lt;element name="OP_COD_STRUTTURA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_NOME_UO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_MANSIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_MANSIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_UBICAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_UBICAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_TIPO_RAPP_LAV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_TIPO_RAPP_LAV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_FLAG_RESPONSABILE" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_CONTRATTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_CONTRATTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_CONTRATTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PROFILO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PROFILO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_POS_ECONOMICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_POS_ECONOMICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_ORARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_ORARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_CAPITOLO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_CAPITOLO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_CASSA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_CASSA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PROGR_ECO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PROGR_ECO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_LIBRO_MATR_INAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_MOD_ASSUNZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_MOD_ASSUNZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_SCAD_CONTR" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_ENTR_RUOLO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_SCAVALCO" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_PART_TIME" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_PART_TIME_V" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_ORARIO_RID" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_REAS_ANNOT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_REAS_ANNOT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "opcodstruttura",
    "opnomeuo",
    "opcodmansione",
    "opdesmansione",
    "opcodubicazione",
    "opdesubicazione",
    "opcodtiporapplav",
    "opdestiporapplav",
    "opflagresponsabile",
    "opcodcontratto",
    "opdescontratto",
    "opdatacontratto",
    "opcodprofilo",
    "opdesprofilo",
    "opcodposeconomica",
    "opdesposeconomica",
    "opcodorario",
    "opdesorario",
    "opcodcapitolo",
    "opdescapitolo",
    "opcodcassa",
    "opdescassa",
    "opcodprogreco",
    "opdesprogreco",
    "oplibromatrinail",
    "opcodmodassunzione",
    "opdesmodassunzione",
    "opdatascadcontr",
    "opdataentrruolo",
    "oppercscavalco",
    "oppercparttime",
    "oppercparttimev",
    "oppercorariorid",
    "opcodreasannot",
    "opdesreasannot",
    "opmsgdata"
})
@XmlRootElement(name = "OutputParameters")
public class OutputParameters {

    @XmlElementRef(name = "OP_COD_STRUTTURA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodstruttura;
    @XmlElementRef(name = "OP_NOME_UO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opnomeuo;
    @XmlElementRef(name = "OP_COD_MANSIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodmansione;
    @XmlElementRef(name = "OP_DES_MANSIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesmansione;
    @XmlElementRef(name = "OP_COD_UBICAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodubicazione;
    @XmlElementRef(name = "OP_DES_UBICAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesubicazione;
    @XmlElementRef(name = "OP_COD_TIPO_RAPP_LAV", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodtiporapplav;
    @XmlElementRef(name = "OP_DES_TIPO_RAPP_LAV", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdestiporapplav;
    @XmlElementRef(name = "OP_FLAG_RESPONSABILE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> opflagresponsabile;
    @XmlElementRef(name = "OP_COD_CONTRATTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodcontratto;
    @XmlElementRef(name = "OP_DES_CONTRATTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdescontratto;
    @XmlElementRef(name = "OP_DATA_CONTRATTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdatacontratto;
    @XmlElementRef(name = "OP_COD_PROFILO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodprofilo;
    @XmlElementRef(name = "OP_DES_PROFILO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesprofilo;
    @XmlElementRef(name = "OP_COD_POS_ECONOMICA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodposeconomica;
    @XmlElementRef(name = "OP_DES_POS_ECONOMICA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesposeconomica;
    @XmlElementRef(name = "OP_COD_ORARIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodorario;
    @XmlElementRef(name = "OP_DES_ORARIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesorario;
    @XmlElementRef(name = "OP_COD_CAPITOLO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodcapitolo;
    @XmlElementRef(name = "OP_DES_CAPITOLO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdescapitolo;
    @XmlElementRef(name = "OP_COD_CASSA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodcassa;
    @XmlElementRef(name = "OP_DES_CASSA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdescassa;
    @XmlElementRef(name = "OP_COD_PROGR_ECO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodprogreco;
    @XmlElementRef(name = "OP_DES_PROGR_ECO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesprogreco;
    @XmlElementRef(name = "OP_LIBRO_MATR_INAIL", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oplibromatrinail;
    @XmlElementRef(name = "OP_COD_MOD_ASSUNZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodmodassunzione;
    @XmlElementRef(name = "OP_DES_MOD_ASSUNZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesmodassunzione;
    @XmlElementRef(name = "OP_DATA_SCAD_CONTR", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdatascadcontr;
    @XmlElementRef(name = "OP_DATA_ENTR_RUOLO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdataentrruolo;
    @XmlElementRef(name = "OP_PERC_SCAVALCO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercscavalco;
    @XmlElementRef(name = "OP_PERC_PART_TIME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercparttime;
    @XmlElementRef(name = "OP_PERC_PART_TIME_V", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercparttimev;
    @XmlElementRef(name = "OP_PERC_ORARIO_RID", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercorariorid;
    @XmlElementRef(name = "OP_COD_REAS_ANNOT", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodreasannot;
    @XmlElementRef(name = "OP_DES_REAS_ANNOT", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesreasannot;
    @XmlElementRef(name = "OP_MSG_DATA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_citta_metro_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmsgdata;

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
     * Recupera il valore della proprietà opnomeuo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPNOMEUO() {
        return opnomeuo;
    }

    /**
     * Imposta il valore della proprietà opnomeuo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPNOMEUO(JAXBElement<String> value) {
        this.opnomeuo = value;
    }

    /**
     * Recupera il valore della proprietà opcodmansione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODMANSIONE() {
        return opcodmansione;
    }

    /**
     * Imposta il valore della proprietà opcodmansione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODMANSIONE(JAXBElement<String> value) {
        this.opcodmansione = value;
    }

    /**
     * Recupera il valore della proprietà opdesmansione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESMANSIONE() {
        return opdesmansione;
    }

    /**
     * Imposta il valore della proprietà opdesmansione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESMANSIONE(JAXBElement<String> value) {
        this.opdesmansione = value;
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
     * Recupera il valore della proprietà opdesubicazione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESUBICAZIONE() {
        return opdesubicazione;
    }

    /**
     * Imposta il valore della proprietà opdesubicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESUBICAZIONE(JAXBElement<String> value) {
        this.opdesubicazione = value;
    }

    /**
     * Recupera il valore della proprietà opcodtiporapplav.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODTIPORAPPLAV() {
        return opcodtiporapplav;
    }

    /**
     * Imposta il valore della proprietà opcodtiporapplav.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODTIPORAPPLAV(JAXBElement<String> value) {
        this.opcodtiporapplav = value;
    }

    /**
     * Recupera il valore della proprietà opdestiporapplav.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESTIPORAPPLAV() {
        return opdestiporapplav;
    }

    /**
     * Imposta il valore della proprietà opdestiporapplav.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESTIPORAPPLAV(JAXBElement<String> value) {
        this.opdestiporapplav = value;
    }

    /**
     * Recupera il valore della proprietà opflagresponsabile.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getOPFLAGRESPONSABILE() {
        return opflagresponsabile;
    }

    /**
     * Imposta il valore della proprietà opflagresponsabile.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setOPFLAGRESPONSABILE(JAXBElement<Integer> value) {
        this.opflagresponsabile = value;
    }

    /**
     * Recupera il valore della proprietà opcodcontratto.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODCONTRATTO() {
        return opcodcontratto;
    }

    /**
     * Imposta il valore della proprietà opcodcontratto.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODCONTRATTO(JAXBElement<String> value) {
        this.opcodcontratto = value;
    }

    /**
     * Recupera il valore della proprietà opdescontratto.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESCONTRATTO() {
        return opdescontratto;
    }

    /**
     * Imposta il valore della proprietà opdescontratto.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESCONTRATTO(JAXBElement<String> value) {
        this.opdescontratto = value;
    }

    /**
     * Recupera il valore della proprietà opdatacontratto.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDATACONTRATTO() {
        return opdatacontratto;
    }

    /**
     * Imposta il valore della proprietà opdatacontratto.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDATACONTRATTO(JAXBElement<String> value) {
        this.opdatacontratto = value;
    }

    /**
     * Recupera il valore della proprietà opcodprofilo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPROFILO() {
        return opcodprofilo;
    }

    /**
     * Imposta il valore della proprietà opcodprofilo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPROFILO(JAXBElement<String> value) {
        this.opcodprofilo = value;
    }

    /**
     * Recupera il valore della proprietà opdesprofilo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPROFILO() {
        return opdesprofilo;
    }

    /**
     * Imposta il valore della proprietà opdesprofilo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPROFILO(JAXBElement<String> value) {
        this.opdesprofilo = value;
    }

    /**
     * Recupera il valore della proprietà opcodposeconomica.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPOSECONOMICA() {
        return opcodposeconomica;
    }

    /**
     * Imposta il valore della proprietà opcodposeconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPOSECONOMICA(JAXBElement<String> value) {
        this.opcodposeconomica = value;
    }

    /**
     * Recupera il valore della proprietà opdesposeconomica.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPOSECONOMICA() {
        return opdesposeconomica;
    }

    /**
     * Imposta il valore della proprietà opdesposeconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPOSECONOMICA(JAXBElement<String> value) {
        this.opdesposeconomica = value;
    }

    /**
     * Recupera il valore della proprietà opcodorario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODORARIO() {
        return opcodorario;
    }

    /**
     * Imposta il valore della proprietà opcodorario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODORARIO(JAXBElement<String> value) {
        this.opcodorario = value;
    }

    /**
     * Recupera il valore della proprietà opdesorario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESORARIO() {
        return opdesorario;
    }

    /**
     * Imposta il valore della proprietà opdesorario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESORARIO(JAXBElement<String> value) {
        this.opdesorario = value;
    }

    /**
     * Recupera il valore della proprietà opcodcapitolo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODCAPITOLO() {
        return opcodcapitolo;
    }

    /**
     * Imposta il valore della proprietà opcodcapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODCAPITOLO(JAXBElement<String> value) {
        this.opcodcapitolo = value;
    }

    /**
     * Recupera il valore della proprietà opdescapitolo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESCAPITOLO() {
        return opdescapitolo;
    }

    /**
     * Imposta il valore della proprietà opdescapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESCAPITOLO(JAXBElement<String> value) {
        this.opdescapitolo = value;
    }

    /**
     * Recupera il valore della proprietà opcodcassa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODCASSA() {
        return opcodcassa;
    }

    /**
     * Imposta il valore della proprietà opcodcassa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODCASSA(JAXBElement<String> value) {
        this.opcodcassa = value;
    }

    /**
     * Recupera il valore della proprietà opdescassa.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESCASSA() {
        return opdescassa;
    }

    /**
     * Imposta il valore della proprietà opdescassa.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESCASSA(JAXBElement<String> value) {
        this.opdescassa = value;
    }

    /**
     * Recupera il valore della proprietà opcodprogreco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPROGRECO() {
        return opcodprogreco;
    }

    /**
     * Imposta il valore della proprietà opcodprogreco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPROGRECO(JAXBElement<String> value) {
        this.opcodprogreco = value;
    }

    /**
     * Recupera il valore della proprietà opdesprogreco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPROGRECO() {
        return opdesprogreco;
    }

    /**
     * Imposta il valore della proprietà opdesprogreco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPROGRECO(JAXBElement<String> value) {
        this.opdesprogreco = value;
    }

    /**
     * Recupera il valore della proprietà oplibromatrinail.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPLIBROMATRINAIL() {
        return oplibromatrinail;
    }

    /**
     * Imposta il valore della proprietà oplibromatrinail.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPLIBROMATRINAIL(JAXBElement<String> value) {
        this.oplibromatrinail = value;
    }

    /**
     * Recupera il valore della proprietà opcodmodassunzione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODMODASSUNZIONE() {
        return opcodmodassunzione;
    }

    /**
     * Imposta il valore della proprietà opcodmodassunzione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODMODASSUNZIONE(JAXBElement<String> value) {
        this.opcodmodassunzione = value;
    }

    /**
     * Recupera il valore della proprietà opdesmodassunzione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESMODASSUNZIONE() {
        return opdesmodassunzione;
    }

    /**
     * Imposta il valore della proprietà opdesmodassunzione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESMODASSUNZIONE(JAXBElement<String> value) {
        this.opdesmodassunzione = value;
    }

    /**
     * Recupera il valore della proprietà opdatascadcontr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATASCADCONTR() {
        return opdatascadcontr;
    }

    /**
     * Imposta il valore della proprietà opdatascadcontr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATASCADCONTR(JAXBElement<XMLGregorianCalendar> value) {
        this.opdatascadcontr = value;
    }

    /**
     * Recupera il valore della proprietà opdataentrruolo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAENTRRUOLO() {
        return opdataentrruolo;
    }

    /**
     * Imposta il valore della proprietà opdataentrruolo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAENTRRUOLO(JAXBElement<XMLGregorianCalendar> value) {
        this.opdataentrruolo = value;
    }

    /**
     * Recupera il valore della proprietà oppercscavalco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCSCAVALCO() {
        return oppercscavalco;
    }

    /**
     * Imposta il valore della proprietà oppercscavalco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCSCAVALCO(JAXBElement<BigDecimal> value) {
        this.oppercscavalco = value;
    }

    /**
     * Recupera il valore della proprietà oppercparttime.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCPARTTIME() {
        return oppercparttime;
    }

    /**
     * Imposta il valore della proprietà oppercparttime.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCPARTTIME(JAXBElement<BigDecimal> value) {
        this.oppercparttime = value;
    }

    /**
     * Recupera il valore della proprietà oppercparttimev.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCPARTTIMEV() {
        return oppercparttimev;
    }

    /**
     * Imposta il valore della proprietà oppercparttimev.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCPARTTIMEV(JAXBElement<BigDecimal> value) {
        this.oppercparttimev = value;
    }

    /**
     * Recupera il valore della proprietà oppercorariorid.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCORARIORID() {
        return oppercorariorid;
    }

    /**
     * Imposta il valore della proprietà oppercorariorid.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCORARIORID(JAXBElement<BigDecimal> value) {
        this.oppercorariorid = value;
    }

    /**
     * Recupera il valore della proprietà opcodreasannot.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODREASANNOT() {
        return opcodreasannot;
    }

    /**
     * Imposta il valore della proprietà opcodreasannot.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODREASANNOT(JAXBElement<String> value) {
        this.opcodreasannot = value;
    }

    /**
     * Recupera il valore della proprietà opdesreasannot.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESREASANNOT() {
        return opdesreasannot;
    }

    /**
     * Imposta il valore della proprietà opdesreasannot.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESREASANNOT(JAXBElement<String> value) {
        this.opdesreasannot = value;
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
