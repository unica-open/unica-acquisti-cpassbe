
package it.csi.siac.documenti.svc._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.csi.siac.integ.data._1.BaseRequest;


/**
 * &lt;p&gt;Classe Java per leggiStatoElaborazioneDocumento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="leggiStatoElaborazioneDocumento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idOperazioneAsincrona" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "leggiStatoElaborazioneDocumento", propOrder = {
    "idOperazioneAsincrona"
})
public class LeggiStatoElaborazioneDocumento
    extends BaseRequest
{

    protected Integer idOperazioneAsincrona;

    /**
     * Recupera il valore della proprietà idOperazioneAsincrona.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdOperazioneAsincrona() {
        return idOperazioneAsincrona;
    }

    /**
     * Imposta il valore della proprietà idOperazioneAsincrona.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdOperazioneAsincrona(Integer value) {
        this.idOperazioneAsincrona = value;
    }

}
