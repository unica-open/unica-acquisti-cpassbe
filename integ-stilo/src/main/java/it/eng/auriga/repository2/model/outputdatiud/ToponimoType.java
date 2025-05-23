
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ToponimoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ToponimoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodToponomastico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescrizioneToponimo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ToponimoType", propOrder = {
    "codToponomastico",
    "descrizioneToponimo"
})
public class ToponimoType {

    @XmlElement(name = "CodToponomastico")
    protected String codToponomastico;
    @XmlElement(name = "DescrizioneToponimo", required = true)
    protected String descrizioneToponimo;

    /**
     * Recupera il valore della proprietà codToponomastico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodToponomastico() {
        return codToponomastico;
    }

    /**
     * Imposta il valore della proprietà codToponomastico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodToponomastico(String value) {
        this.codToponomastico = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneToponimo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneToponimo() {
        return descrizioneToponimo;
    }

    /**
     * Imposta il valore della proprietà descrizioneToponimo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneToponimo(String value) {
        this.descrizioneToponimo = value;
    }

}
