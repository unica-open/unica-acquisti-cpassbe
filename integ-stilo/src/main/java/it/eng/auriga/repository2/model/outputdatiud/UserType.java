
package it.eng.auriga.repository2.model.outputdatiud;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per UserType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="UserType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdInSistemaEsterno" type="{}IdInSistemaEsternoType" minOccurs="0"/&gt;
 *         &lt;element name="IdInterno" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="Decodifica_CognomeNome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserType", propOrder = {
    "idInSistemaEsterno",
    "idInterno",
    "decodificaCognomeNome"
})
public class UserType {

    @XmlElement(name = "IdInSistemaEsterno")
    protected String idInSistemaEsterno;
    @XmlElement(name = "IdInterno", required = true)
    protected BigInteger idInterno;
    @XmlElement(name = "Decodifica_CognomeNome", required = true)
    protected String decodificaCognomeNome;

    /**
     * Recupera il valore della proprietà idInSistemaEsterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdInSistemaEsterno() {
        return idInSistemaEsterno;
    }

    /**
     * Imposta il valore della proprietà idInSistemaEsterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdInSistemaEsterno(String value) {
        this.idInSistemaEsterno = value;
    }

    /**
     * Recupera il valore della proprietà idInterno.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdInterno() {
        return idInterno;
    }

    /**
     * Imposta il valore della proprietà idInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdInterno(BigInteger value) {
        this.idInterno = value;
    }

    /**
     * Recupera il valore della proprietà decodificaCognomeNome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecodificaCognomeNome() {
        return decodificaCognomeNome;
    }

    /**
     * Imposta il valore della proprietà decodificaCognomeNome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecodificaCognomeNome(String value) {
        this.decodificaCognomeNome = value;
    }

}
