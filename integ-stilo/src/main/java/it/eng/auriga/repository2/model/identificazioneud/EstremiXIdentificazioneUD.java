
package it.eng.auriga.repository2.model.identificazioneud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}EstremiXIdentificazioneUDType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="OutputValidoAllaDataOra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "outputValidoAllaDataOra"
})
@XmlRootElement(name = "EstremiXIdentificazioneUD")
public class EstremiXIdentificazioneUD
    extends EstremiXIdentificazioneUDType
{

    @XmlElement(name = "OutputValidoAllaDataOra")
    protected String outputValidoAllaDataOra;

    /**
     * Recupera il valore della proprietà outputValidoAllaDataOra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputValidoAllaDataOra() {
        return outputValidoAllaDataOra;
    }

    /**
     * Imposta il valore della proprietà outputValidoAllaDataOra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputValidoAllaDataOra(String value) {
        this.outputValidoAllaDataOra = value;
    }

}
