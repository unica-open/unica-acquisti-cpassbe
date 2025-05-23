
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per UOEstesaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="UOEstesaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}UOType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FlagIncluseSottoUO" type="{}FlagSiNoType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UOEstesaType", propOrder = {
    "flagIncluseSottoUO"
})
public class UOEstesaType
    extends UOType
{

    @XmlElement(name = "FlagIncluseSottoUO", required = true, defaultValue = "0")
    protected String flagIncluseSottoUO;

    /**
     * Recupera il valore della proprietà flagIncluseSottoUO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagIncluseSottoUO() {
        return flagIncluseSottoUO;
    }

    /**
     * Imposta il valore della proprietà flagIncluseSottoUO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagIncluseSottoUO(String value) {
        this.flagIncluseSottoUO = value;
    }

}
