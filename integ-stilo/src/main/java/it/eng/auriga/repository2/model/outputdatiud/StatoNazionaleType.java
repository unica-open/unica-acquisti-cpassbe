//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per StatoNazionaleType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="StatoNazionaleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodISTATStato" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]{3}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NomeStato" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatoNazionaleType", propOrder = {
    "codISTATStato",
    "nomeStato"
})
public class StatoNazionaleType {

    @XmlElement(name = "CodISTATStato")
    protected String codISTATStato;
    @XmlElement(name = "NomeStato", required = true)
    protected String nomeStato;

    /**
     * Recupera il valore della proprietà codISTATStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodISTATStato() {
        return codISTATStato;
    }

    /**
     * Imposta il valore della proprietà codISTATStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodISTATStato(String value) {
        this.codISTATStato = value;
    }

    /**
     * Recupera il valore della proprietà nomeStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeStato() {
        return nomeStato;
    }

    /**
     * Imposta il valore della proprietà nomeStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeStato(String value) {
        this.nomeStato = value;
    }

}
