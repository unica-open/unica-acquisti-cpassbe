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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Rappresenta un oggetto censito in una tabella di sistema del sistema documentale
 * 
 * <p>Classe Java per OggDiTabDiSistemaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="OggDiTabDiSistemaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Decodifica_Nome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OggDiTabDiSistemaType", propOrder = {
    "codId",
    "decodificaNome"
})
@XmlSeeAlso({
    it.eng.auriga.repository2.model.outputdatiud.DatiUD.Stato.class,
    it.eng.auriga.repository2.model.outputdatiud.DatiUD.StatoDettaglio.class
})
public class OggDiTabDiSistemaType {

    @XmlElement(name = "CodId", required = true)
    protected String codId;
    @XmlElement(name = "Decodifica_Nome", required = true)
    protected String decodificaNome;

    /**
     * Recupera il valore della proprietà codId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodId() {
        return codId;
    }

    /**
     * Imposta il valore della proprietà codId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodId(String value) {
        this.codId = value;
    }

    /**
     * Recupera il valore della proprietà decodificaNome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecodificaNome() {
        return decodificaNome;
    }

    /**
     * Imposta il valore della proprietà decodificaNome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecodificaNome(String value) {
        this.decodificaNome = value;
    }

}
