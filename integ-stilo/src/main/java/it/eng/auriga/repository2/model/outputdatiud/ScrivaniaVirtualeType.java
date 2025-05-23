
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * La scrivania virtuale rappresenta un utente non come persona fisica, ma nella funzione che svolge presso una certa UO. Può essere indicata univocamente indicando UO e utente; oppure il sistema può cercare di identificarla anche o solo a partire dalla sua descrizione
 * 
 * <p>Classe Java per ScrivaniaVirtualeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ScrivaniaVirtualeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="UO" type="{}UOType"/&gt;
 *         &lt;element name="Utente" type="{}UserType"/&gt;
 *         &lt;element name="DesScrivaniaVirt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScrivaniaVirtualeType", propOrder = {

})
public class ScrivaniaVirtualeType {

    @XmlElement(name = "UO", required = true)
    protected UOType uo;
    @XmlElement(name = "Utente", required = true)
    protected UserType utente;
    @XmlElement(name = "DesScrivaniaVirt", required = true)
    protected String desScrivaniaVirt;

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

    /**
     * Recupera il valore della proprietà desScrivaniaVirt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesScrivaniaVirt() {
        return desScrivaniaVirt;
    }

    /**
     * Imposta il valore della proprietà desScrivaniaVirt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesScrivaniaVirt(String value) {
        this.desScrivaniaVirt = value;
    }

}
