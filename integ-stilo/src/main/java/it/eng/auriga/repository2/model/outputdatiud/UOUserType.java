
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per UOUserType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="UOUserType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="UO" type="{}UOType"/&gt;
 *         &lt;element name="Utente" type="{}UserType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UOUserType", propOrder = {
    "uo",
    "utente"
})
public class UOUserType {

    @XmlElement(name = "UO")
    protected UOType uo;
    @XmlElement(name = "Utente")
    protected UserType utente;

    /**
     * Recupera il valore della proprietà uo.
     * 
     * @return
     *     possible object is
     *     {@link UOType }
     *     
     */
    public UOType getUO() {
        return uo;
    }

    /**
     * Imposta il valore della proprietà uo.
     * 
     * @param value
     *     allowed object is
     *     {@link UOType }
     *     
     */
    public void setUO(UOType value) {
        this.uo = value;
    }

    /**
     * Recupera il valore della proprietà utente.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    public UserType getUtente() {
        return utente;
    }

    /**
     * Imposta il valore della proprietà utente.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    public void setUtente(UserType value) {
        this.utente = value;
    }

}
