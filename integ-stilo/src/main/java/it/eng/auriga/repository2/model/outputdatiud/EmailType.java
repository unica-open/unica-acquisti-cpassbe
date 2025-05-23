
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per EmailType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EmailType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="IndirizzoEmail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FlagPEC" type="{}FlagSiNoType" minOccurs="0"/&gt;
 *         &lt;element name="FlagCasellaIstituzionaleAOO" type="{}FlagSiNoType" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmailType", propOrder = {

})
public class EmailType {

    @XmlElement(name = "IndirizzoEmail", required = true)
    protected String indirizzoEmail;
    @XmlElement(name = "FlagPEC")
    protected String flagPEC;
    @XmlElement(name = "FlagCasellaIstituzionaleAOO")
    protected String flagCasellaIstituzionaleAOO;

    /**
     * Recupera il valore della proprietà indirizzoEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    /**
     * Imposta il valore della proprietà indirizzoEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoEmail(String value) {
        this.indirizzoEmail = value;
    }

    /**
     * Recupera il valore della proprietà flagPEC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagPEC() {
        return flagPEC;
    }

    /**
     * Imposta il valore della proprietà flagPEC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagPEC(String value) {
        this.flagPEC = value;
    }

    /**
     * Recupera il valore della proprietà flagCasellaIstituzionaleAOO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagCasellaIstituzionaleAOO() {
        return flagCasellaIstituzionaleAOO;
    }

    /**
     * Imposta il valore della proprietà flagCasellaIstituzionaleAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagCasellaIstituzionaleAOO(String value) {
        this.flagCasellaIstituzionaleAOO = value;
    }

}
