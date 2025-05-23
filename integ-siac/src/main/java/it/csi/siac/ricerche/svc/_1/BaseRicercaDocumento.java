
package it.csi.siac.ricerche.svc._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.siac.integ.data._1.RicercaPaginataRequest;


/**
 * &lt;p&gt;Classe Java per baseRicercaDocumento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="baseRicercaDocumento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}ricercaPaginataRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annoDocumento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="annoRepertorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceSoggetto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataRepertorio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroRepertorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="registroRepertorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseRicercaDocumento", propOrder = {
    "annoDocumento",
    "annoRepertorio",
    "codiceSoggetto",
    "dataRepertorio",
    "numeroDocumento",
    "numeroRepertorio",
    "registroRepertorio",
    "tipoDocumento"
})
@XmlSeeAlso({
    RicercaDocumentoSpesa.class,
    RicercaDocumentoEntrata.class
})
public abstract class BaseRicercaDocumento
    extends RicercaPaginataRequest
{

    protected Integer annoDocumento;
    protected Integer annoRepertorio;
    protected String codiceSoggetto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRepertorio;
    protected String numeroDocumento;
    protected String numeroRepertorio;
    protected String registroRepertorio;
    protected String tipoDocumento;

    /**
     * Recupera il valore della proprietà annoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoDocumento() {
        return annoDocumento;
    }

    /**
     * Imposta il valore della proprietà annoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoDocumento(Integer value) {
        this.annoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà annoRepertorio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoRepertorio() {
        return annoRepertorio;
    }

    /**
     * Imposta il valore della proprietà annoRepertorio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoRepertorio(Integer value) {
        this.annoRepertorio = value;
    }

    /**
     * Recupera il valore della proprietà codiceSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceSoggetto() {
        return codiceSoggetto;
    }

    /**
     * Imposta il valore della proprietà codiceSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceSoggetto(String value) {
        this.codiceSoggetto = value;
    }

    /**
     * Recupera il valore della proprietà dataRepertorio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRepertorio() {
        return dataRepertorio;
    }

    /**
     * Imposta il valore della proprietà dataRepertorio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRepertorio(XMLGregorianCalendar value) {
        this.dataRepertorio = value;
    }

    /**
     * Recupera il valore della proprietà numeroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Imposta il valore della proprietà numeroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Recupera il valore della proprietà numeroRepertorio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRepertorio() {
        return numeroRepertorio;
    }

    /**
     * Imposta il valore della proprietà numeroRepertorio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRepertorio(String value) {
        this.numeroRepertorio = value;
    }

    /**
     * Recupera il valore della proprietà registroRepertorio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistroRepertorio() {
        return registroRepertorio;
    }

    /**
     * Imposta il valore della proprietà registroRepertorio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistroRepertorio(String value) {
        this.registroRepertorio = value;
    }

    /**
     * Recupera il valore della proprietà tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Imposta il valore della proprietà tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

}
