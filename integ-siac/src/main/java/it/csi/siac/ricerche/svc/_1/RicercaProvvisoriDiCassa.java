
package it.csi.siac.ricerche.svc._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import it.csi.siac.integ.data._1.RicercaPaginataRequest;
import it.csi.siac.integ.data._1.SiNoEnum;
import it.csi.siac.integ.data._1.SiNoIndifferenteEnum;
import it.csi.siac.integ.data._1.TipoProvvisorioDiCassa;


/**
 * &lt;p&gt;Classe Java per ricercaProvvisoriDiCassa complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaProvvisoriDiCassa"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}ricercaPaginataRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annullato" type="{http://siac.csi.it/integ/data/1.0}siNoIndifferenteEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="daRegolarizzare" type="{http://siac.csi.it/integ/data/1.0}siNoEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataDa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descrizioneCausale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="importoA" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="importoDa" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroQuietanza" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoProvvisorioDiCassa" type="{http://siac.csi.it/integ/data/1.0}tipoProvvisorioDiCassa" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaProvvisoriDiCassa", propOrder = {
    "annullato",
    "daRegolarizzare",
    "dataA",
    "dataDa",
    "descrizioneCausale",
    "importoA",
    "importoDa",
    "numeroProvvisorio",
    "numeroQuietanza",
    "tipoProvvisorioDiCassa"
})
public class RicercaProvvisoriDiCassa
    extends RicercaPaginataRequest
{

    @XmlSchemaType(name = "string")
    protected SiNoIndifferenteEnum annullato;
    @XmlSchemaType(name = "string")
    protected SiNoEnum daRegolarizzare;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDa;
    protected String descrizioneCausale;
    protected BigDecimal importoA;
    protected BigDecimal importoDa;
    protected Integer numeroProvvisorio;
    protected Integer numeroQuietanza;
    @XmlSchemaType(name = "string")
    protected TipoProvvisorioDiCassa tipoProvvisorioDiCassa;

    /**
     * Recupera il valore della proprietà annullato.
     * 
     * @return
     *     possible object is
     *     {@link SiNoIndifferenteEnum }
     *     
     */
    public SiNoIndifferenteEnum getAnnullato() {
        return annullato;
    }

    /**
     * Imposta il valore della proprietà annullato.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNoIndifferenteEnum }
     *     
     */
    public void setAnnullato(SiNoIndifferenteEnum value) {
        this.annullato = value;
    }

    /**
     * Recupera il valore della proprietà daRegolarizzare.
     * 
     * @return
     *     possible object is
     *     {@link SiNoEnum }
     *     
     */
    public SiNoEnum getDaRegolarizzare() {
        return daRegolarizzare;
    }

    /**
     * Imposta il valore della proprietà daRegolarizzare.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNoEnum }
     *     
     */
    public void setDaRegolarizzare(SiNoEnum value) {
        this.daRegolarizzare = value;
    }

    /**
     * Recupera il valore della proprietà dataA.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataA() {
        return dataA;
    }

    /**
     * Imposta il valore della proprietà dataA.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataA(XMLGregorianCalendar value) {
        this.dataA = value;
    }

    /**
     * Recupera il valore della proprietà dataDa.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDa() {
        return dataDa;
    }

    /**
     * Imposta il valore della proprietà dataDa.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDa(XMLGregorianCalendar value) {
        this.dataDa = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneCausale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCausale() {
        return descrizioneCausale;
    }

    /**
     * Imposta il valore della proprietà descrizioneCausale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCausale(String value) {
        this.descrizioneCausale = value;
    }

    /**
     * Recupera il valore della proprietà importoA.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoA() {
        return importoA;
    }

    /**
     * Imposta il valore della proprietà importoA.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoA(BigDecimal value) {
        this.importoA = value;
    }

    /**
     * Recupera il valore della proprietà importoDa.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoDa() {
        return importoDa;
    }

    /**
     * Imposta il valore della proprietà importoDa.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoDa(BigDecimal value) {
        this.importoDa = value;
    }

    /**
     * Recupera il valore della proprietà numeroProvvisorio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroProvvisorio() {
        return numeroProvvisorio;
    }

    /**
     * Imposta il valore della proprietà numeroProvvisorio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroProvvisorio(Integer value) {
        this.numeroProvvisorio = value;
    }

    /**
     * Recupera il valore della proprietà numeroQuietanza.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroQuietanza() {
        return numeroQuietanza;
    }

    /**
     * Imposta il valore della proprietà numeroQuietanza.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroQuietanza(Integer value) {
        this.numeroQuietanza = value;
    }

    /**
     * Recupera il valore della proprietà tipoProvvisorioDiCassa.
     * 
     * @return
     *     possible object is
     *     {@link TipoProvvisorioDiCassa }
     *     
     */
    public TipoProvvisorioDiCassa getTipoProvvisorioDiCassa() {
        return tipoProvvisorioDiCassa;
    }

    /**
     * Imposta il valore della proprietà tipoProvvisorioDiCassa.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoProvvisorioDiCassa }
     *     
     */
    public void setTipoProvvisorioDiCassa(TipoProvvisorioDiCassa value) {
        this.tipoProvvisorioDiCassa = value;
    }

}
