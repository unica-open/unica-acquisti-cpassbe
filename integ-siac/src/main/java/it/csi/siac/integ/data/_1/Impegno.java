
package it.csi.siac.integ.data._1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per impegno complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="impegno"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}movimentoGestione"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annoImpegno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="annoImpegnoOrigine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="annoImpegnoRiaccertato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="disponibilitaLiquidare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numImpegnoOrigine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numImpegnoRiaccertato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroImpegno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="subImpegni" type="{http://siac.csi.it/integ/data/1.0}subImpegno" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoImpegno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "impegno", propOrder = {
    "annoImpegno",
    "annoImpegnoOrigine",
    "annoImpegnoRiaccertato",
    "disponibilitaLiquidare",
    "numImpegnoOrigine",
    "numImpegnoRiaccertato",
    "numeroImpegno",
    "subImpegni",
    "tipoImpegno"
})
public class Impegno
    extends MovimentoGestione
{

    protected Integer annoImpegno;
    protected Integer annoImpegnoOrigine;
    protected Integer annoImpegnoRiaccertato;
    protected BigDecimal disponibilitaLiquidare;
    protected Integer numImpegnoOrigine;
    protected Integer numImpegnoRiaccertato;
    protected Integer numeroImpegno;
    @XmlElement(nillable = true)
    protected List<SubImpegno> subImpegni;
    protected String tipoImpegno;

    /**
     * Recupera il valore della proprietà annoImpegno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoImpegno() {
        return annoImpegno;
    }

    /**
     * Imposta il valore della proprietà annoImpegno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoImpegno(Integer value) {
        this.annoImpegno = value;
    }

    /**
     * Recupera il valore della proprietà annoImpegnoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoImpegnoOrigine() {
        return annoImpegnoOrigine;
    }

    /**
     * Imposta il valore della proprietà annoImpegnoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoImpegnoOrigine(Integer value) {
        this.annoImpegnoOrigine = value;
    }

    /**
     * Recupera il valore della proprietà annoImpegnoRiaccertato.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoImpegnoRiaccertato() {
        return annoImpegnoRiaccertato;
    }

    /**
     * Imposta il valore della proprietà annoImpegnoRiaccertato.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoImpegnoRiaccertato(Integer value) {
        this.annoImpegnoRiaccertato = value;
    }

    /**
     * Recupera il valore della proprietà disponibilitaLiquidare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDisponibilitaLiquidare() {
        return disponibilitaLiquidare;
    }

    /**
     * Imposta il valore della proprietà disponibilitaLiquidare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDisponibilitaLiquidare(BigDecimal value) {
        this.disponibilitaLiquidare = value;
    }

    /**
     * Recupera il valore della proprietà numImpegnoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumImpegnoOrigine() {
        return numImpegnoOrigine;
    }

    /**
     * Imposta il valore della proprietà numImpegnoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumImpegnoOrigine(Integer value) {
        this.numImpegnoOrigine = value;
    }

    /**
     * Recupera il valore della proprietà numImpegnoRiaccertato.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumImpegnoRiaccertato() {
        return numImpegnoRiaccertato;
    }

    /**
     * Imposta il valore della proprietà numImpegnoRiaccertato.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumImpegnoRiaccertato(Integer value) {
        this.numImpegnoRiaccertato = value;
    }

    /**
     * Recupera il valore della proprietà numeroImpegno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroImpegno() {
        return numeroImpegno;
    }

    /**
     * Imposta il valore della proprietà numeroImpegno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroImpegno(Integer value) {
        this.numeroImpegno = value;
    }

    /**
     * Gets the value of the subImpegni property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the subImpegni property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getSubImpegni().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link SubImpegno }
     * 
     * 
     */
    public List<SubImpegno> getSubImpegni() {
        if (subImpegni == null) {
            subImpegni = new ArrayList<SubImpegno>();
        }
        return this.subImpegni;
    }

    /**
     * Recupera il valore della proprietà tipoImpegno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoImpegno() {
        return tipoImpegno;
    }

    /**
     * Imposta il valore della proprietà tipoImpegno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoImpegno(String value) {
        this.tipoImpegno = value;
    }

}
