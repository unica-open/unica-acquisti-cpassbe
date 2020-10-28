
package it.csi.siac.ricerche.data._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import it.csi.siac.integ.data._1.EntitaCodificataBase;
import it.csi.siac.integ.data._1.SiNoEnum;
import it.csi.siac.integ.data._1.TipoProvvisorioDiCassa;


/**
 * &lt;p&gt;Classe Java per provvisorioDiCassa complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="provvisorioDiCassa"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}entitaCodificataBase"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="anno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="annullato" type="{http://siac.csi.it/integ/data/1.0}siNoEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="importoDaEmettere" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="importoDaRegolarizzare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
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
@XmlType(name = "provvisorioDiCassa", propOrder = {
    "anno",
    "annullato",
    "causale",
    "data",
    "importo",
    "importoDaEmettere",
    "importoDaRegolarizzare",
    "numero",
    "tipoProvvisorioDiCassa"
})
public class ProvvisorioDiCassa
    extends EntitaCodificataBase
{

    protected Integer anno;
    @XmlSchemaType(name = "string")
    protected SiNoEnum annullato;
    protected String causale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    protected BigDecimal importo;
    protected BigDecimal importoDaEmettere;
    protected BigDecimal importoDaRegolarizzare;
    protected Integer numero;
    @XmlSchemaType(name = "string")
    protected TipoProvvisorioDiCassa tipoProvvisorioDiCassa;

    /**
     * Recupera il valore della proprietà anno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Imposta il valore della proprietà anno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnno(Integer value) {
        this.anno = value;
    }

    /**
     * Recupera il valore della proprietà annullato.
     * 
     * @return
     *     possible object is
     *     {@link SiNoEnum }
     *     
     */
    public SiNoEnum getAnnullato() {
        return annullato;
    }

    /**
     * Imposta il valore della proprietà annullato.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNoEnum }
     *     
     */
    public void setAnnullato(SiNoEnum value) {
        this.annullato = value;
    }

    /**
     * Recupera il valore della proprietà causale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausale() {
        return causale;
    }

    /**
     * Imposta il valore della proprietà causale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausale(String value) {
        this.causale = value;
    }

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietà importo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporto() {
        return importo;
    }

    /**
     * Imposta il valore della proprietà importo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporto(BigDecimal value) {
        this.importo = value;
    }

    /**
     * Recupera il valore della proprietà importoDaEmettere.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoDaEmettere() {
        return importoDaEmettere;
    }

    /**
     * Imposta il valore della proprietà importoDaEmettere.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoDaEmettere(BigDecimal value) {
        this.importoDaEmettere = value;
    }

    /**
     * Recupera il valore della proprietà importoDaRegolarizzare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoDaRegolarizzare() {
        return importoDaRegolarizzare;
    }

    /**
     * Imposta il valore della proprietà importoDaRegolarizzare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoDaRegolarizzare(BigDecimal value) {
        this.importoDaRegolarizzare = value;
    }

    /**
     * Recupera il valore della proprietà numero.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Imposta il valore della proprietà numero.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumero(Integer value) {
        this.numero = value;
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
