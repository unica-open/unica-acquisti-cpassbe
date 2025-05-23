
package it.eng.auriga.repository2.model.searchoutput;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PaginazioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PaginazioneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroPagina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="NroRecordInPagina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaginazioneType", propOrder = {
    "nroPagina",
    "nroRecordInPagina"
})
public class PaginazioneType {

    @XmlElement(name = "NroPagina", defaultValue = "1")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nroPagina;
    @XmlElement(name = "NroRecordInPagina")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nroRecordInPagina;

    /**
     * Recupera il valore della proprietà nroPagina.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroPagina() {
        return nroPagina;
    }

    /**
     * Imposta il valore della proprietà nroPagina.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroPagina(BigInteger value) {
        this.nroPagina = value;
    }

    /**
     * Recupera il valore della proprietà nroRecordInPagina.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroRecordInPagina() {
        return nroRecordInPagina;
    }

    /**
     * Imposta il valore della proprietà nroRecordInPagina.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroRecordInPagina(BigInteger value) {
        this.nroRecordInPagina = value;
    }

}
