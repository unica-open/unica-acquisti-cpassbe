
package it.eng.auriga.repository2.model.identificazioneud;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Contiene i dati attraverso cui identificare univocamente un'unità documentaria
 * 
 * <p>Classe Java per EstremiXIdentificazioneUDType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EstremiXIdentificazioneUDType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="IdUD" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="EstremiRegNum" type="{}EstremiRegNumType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstremiXIdentificazioneUDType", propOrder = {
    "idUD",
    "estremiRegNum"
})
@XmlSeeAlso({
    EstremiXIdentificazioneUD.class
})
public class EstremiXIdentificazioneUDType {

    @XmlElement(name = "IdUD")
    protected BigInteger idUD;
    @XmlElement(name = "EstremiRegNum")
    protected EstremiRegNumType estremiRegNum;

    /**
     * Recupera il valore della proprietà idUD.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdUD() {
        return idUD;
    }

    /**
     * Imposta il valore della proprietà idUD.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdUD(BigInteger value) {
        this.idUD = value;
    }

    /**
     * Recupera il valore della proprietà estremiRegNum.
     * 
     * @return
     *     possible object is
     *     {@link EstremiRegNumType }
     *     
     */
    public EstremiRegNumType getEstremiRegNum() {
        return estremiRegNum;
    }

    /**
     * Imposta il valore della proprietà estremiRegNum.
     * 
     * @param value
     *     allowed object is
     *     {@link EstremiRegNumType }
     *     
     */
    public void setEstremiRegNum(EstremiRegNumType value) {
        this.estremiRegNum = value;
    }

}
