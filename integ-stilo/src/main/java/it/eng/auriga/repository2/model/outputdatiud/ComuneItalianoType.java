
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ComuneItalianoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ComuneItalianoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodISTATComune" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]{6}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NomeComune" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComuneItalianoType", propOrder = {
    "codISTATComune",
    "nomeComune"
})
public class ComuneItalianoType {

    @XmlElement(name = "CodISTATComune")
    protected String codISTATComune;
    @XmlElement(name = "NomeComune", required = true)
    protected String nomeComune;

    /**
     * Recupera il valore della proprietà codISTATComune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodISTATComune() {
        return codISTATComune;
    }

    /**
     * Imposta il valore della proprietà codISTATComune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodISTATComune(String value) {
        this.codISTATComune = value;
    }

    /**
     * Recupera il valore della proprietà nomeComune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeComune() {
        return nomeComune;
    }

    /**
     * Imposta il valore della proprietà nomeComune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeComune(String value) {
        this.nomeComune = value;
    }

}
