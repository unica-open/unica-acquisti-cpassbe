
package it.csi.siac.integ.data._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.documenti.svc._1.ElaboraDocumento;
import it.csi.siac.documenti.svc._1.LeggiStatoElaborazioneDocumento;


/**
 * &lt;p&gt;Classe Java per baseRequest complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="baseRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annoBilancio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceFruitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseRequest", propOrder = {
    "annoBilancio",
    "codiceEnte",
    "codiceFruitore"
})
@XmlSeeAlso({
    ElaboraDocumento.class,
    LeggiStatoElaborazioneDocumento.class
})
public abstract class BaseRequest {

    protected Integer annoBilancio;
    protected String codiceEnte;
    protected String codiceFruitore;

    /**
     * Recupera il valore della proprietà annoBilancio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoBilancio() {
        return annoBilancio;
    }

    /**
     * Imposta il valore della proprietà annoBilancio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoBilancio(Integer value) {
        this.annoBilancio = value;
    }

    /**
     * Recupera il valore della proprietà codiceEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEnte() {
        return codiceEnte;
    }

    /**
     * Imposta il valore della proprietà codiceEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEnte(String value) {
        this.codiceEnte = value;
    }

    /**
     * Recupera il valore della proprietà codiceFruitore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFruitore() {
        return codiceFruitore;
    }

    /**
     * Imposta il valore della proprietà codiceFruitore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFruitore(String value) {
        this.codiceFruitore = value;
    }

}
