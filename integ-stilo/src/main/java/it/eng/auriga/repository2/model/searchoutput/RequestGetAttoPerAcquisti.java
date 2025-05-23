
package it.eng.auriga.repository2.model.searchoutput;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per RequestGetAttoPerAcquisti complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RequestGetAttoPerAcquisti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SiglaRegistro" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Anno" type="{http://www.w3.org/2001/XMLSchema}gYear"/&gt;
 *         &lt;element name="Nro" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestGetAttoPerAcquisti", propOrder = {
    "siglaRegistro",
    "anno",
    "nro"
})
public class RequestGetAttoPerAcquisti {

    @XmlElement(name = "SiglaRegistro", required = true)
    protected String siglaRegistro;
    
    @XmlElement(name = "Anno", required = true)
    @XmlSchemaType(name = "gYear")
    //protected XMLGregorianCalendar anno;
    protected Integer anno;

    @XmlElement(name = "Nro", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nro;

    /**
     * Recupera il valore della proprietà siglaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaRegistro() {
        return siglaRegistro;
    }

    /**
     * Imposta il valore della proprietà siglaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaRegistro(String value) {
        this.siglaRegistro = value;
    }

    /**
     * Recupera il valore della proprietà anno.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
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
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnno(Integer value) {
        this.anno = value;
    }

    /**
     * Recupera il valore della proprietà nro.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNro() {
        return nro;
    }

    /**
     * Imposta il valore della proprietà nro.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNro(BigInteger value) {
        this.nro = value;
    }

}
